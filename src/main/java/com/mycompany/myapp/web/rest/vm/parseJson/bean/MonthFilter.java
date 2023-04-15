package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class MonthFilter {

    private String name;

    private int value;

    public MonthFilter(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public MonthFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MonthFilter addValue(MainForMethods name) {
        value = value + name.getValue();
        return this;
    }

    @Override
    public String toString() {
        return "Month of Year{" + "Year='" + name + '\'' + ", Value Of Month=" + value + '}';
    }
}
