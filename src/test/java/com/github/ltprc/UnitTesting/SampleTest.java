package com.github.ltprc.UnitTesting;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({})
public class SampleTest extends TestCase {
    
    Sample sample;
    
    @Before
    public void init() {
        
        initSample(sample, 0, "NAME");
    }
    
    /**
     * 定义对象
     * @param sample
     * @param type
     * @param name
     */
    private void initSample(Sample sample, int type, String name) {
        
        switch (type) {
        case 0:
            //对于一些参数不多，逻辑不复杂的测试对象，可以不适用PowerMock框架，直接new一个新实例用于测试。
            sample = new Sample();
            sample.setName(name);
        case 1:
            //mock出的对象可以自行定义特定函数的返回值、跳过逻辑执行。未定义的函数不执行任何逻辑、返回值为空。
            sample = PowerMockito.mock(Sample.class);
            PowerMockito.when(sample.getName()).thenReturn(name);
        case 2:
            //spy出的对象可以自行定义特定函数的返回值、跳过逻辑执行。未定义的函数执行函数原有逻辑、返回值不一定为空。
            sample = PowerMockito.spy(new Sample());
            PowerMockito.doReturn(name).when(sample).getName();
        }
    }
}
