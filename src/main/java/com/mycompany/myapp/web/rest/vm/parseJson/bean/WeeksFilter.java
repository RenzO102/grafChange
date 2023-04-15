package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class WeeksFilter {

    private String name;

    private int value;

    public WeeksFilter(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public WeeksFilter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public WeeksFilter addValue(MainForMethods name) {
        value = value + name.getValue();
        return this;
    }

    @Override
    public String toString() {
        return "Weeks of Year{" + "Year='" + name + '\'' + ", Value Of Weeks=" + value + '}';
    }
}
