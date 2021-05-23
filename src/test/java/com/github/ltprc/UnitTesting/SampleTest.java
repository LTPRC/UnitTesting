package com.github.ltprc.UnitTesting;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({})
public class SampleTest extends TestCase {
    
    @Test
    public void testSampleMethods() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        /**
         * 定义被测对象
         */
        Sample sample;
        String sampleName = "SAMPLE";
        switch (0) {
        case 0:
            //对于一些参数不多，逻辑不复杂的测试对象，可以不适用PowerMock框架，直接new一个新实例用于测试。
            sample = new Sample();
            sample.setName(sampleName);
            break;
        case 1:
            //mock出的对象可以自行定义特定函数的返回值、跳过逻辑执行。未定义的函数不执行任何逻辑、返回值为空。
            sample = PowerMockito.mock(Sample.class);
            PowerMockito.when(sample.getName()).thenReturn(sampleName);
            break;
        case 2:
            //spy出的对象可以自行定义特定函数的返回值、跳过逻辑执行。未定义的函数执行函数原有逻辑、返回值不一定为空。
            sample = PowerMockito.spy(new Sample());
            PowerMockito.doReturn(sampleName).when(sample).getName();
            break;
        default:
            sample = null;
        }
        
        /**
         * 定义依赖对象
         */
        Sample subSample = PowerMockito.mock(Sample.class);
        
        /**
         * 注入依赖对象
         */
        sample.setSubSample(subSample);
        
        /**
         * 模拟方法
         */
        String subSampleName = "SUBSAMPLE";
        PowerMockito.when(subSample.getName()).thenReturn(subSampleName);
        
        /**
         * 调用方法
         */
        String actualName;
        switch (0) {
        case 0:
            //直接调用
            actualName = sample.getSubSample().getName();
            break;
        case 1:
            //通过反射调用
            Method getOutput = Sample.class.getDeclaredMethod("getName");
            getOutput.setAccessible(true);
            actualName = (String) getOutput.invoke(sample.getSubSample());
            break;
        default:
            actualName = null;
        }
        
        /**
         * 验证状态
         */
        Assert.assertEquals(subSampleName, actualName);
        
        /**
         * 验证行为
         */
        Mockito.verify(sample.getSubSample(), Mockito.times(1)).getName();
    }
}
