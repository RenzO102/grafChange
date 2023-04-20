package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class MonthsFilter {

    private int value;

    public MonthsFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MonthsFilter addValue(MainForMethods name) {
        value = value + name.getValue();
        return this;
    }

    @Override
    public String toString() {
        return "MonthsFilter{" + "value=" + value + '}';
    }
}
