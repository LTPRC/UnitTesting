package com.github.ltprc.UnitTesting;

/**
 * Class sample for unit-testing.
 * @author tuoli
 *
 */
public class Sample {
    
    private int num;
    private String name;
    private Sample subSample;
    
    public Sample() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sample getSubSample() {
        return subSample;
    }

    public void setSubSample(Sample subSample) {
        this.subSample = subSample;
    }
}
