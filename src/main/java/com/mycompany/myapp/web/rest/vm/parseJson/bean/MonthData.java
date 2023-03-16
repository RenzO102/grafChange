package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class MonthData {

    private String name;

    private int value = 0;

    public MonthData(String name) {
        this.name = name;
    }

    public MonthData addValue() {
        value++;

        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
