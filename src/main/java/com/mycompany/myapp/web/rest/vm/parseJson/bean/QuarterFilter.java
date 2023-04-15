package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class QuarterFilter {

    private String name;

    private int value;

    public QuarterFilter(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public QuarterFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public QuarterFilter addValue(MainForMethods name) {
        value = value + name.getValue();
        return this;
    }

    @Override
    public String toString() {
        return "Quarters of Year{" + "Year='" + name + '\'' + ", Value Of Quarters=" + value + '}';
    }
}
