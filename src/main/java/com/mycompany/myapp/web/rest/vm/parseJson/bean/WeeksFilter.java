package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class WeeksFilter {

    private int value;

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
        return "WeeksFilter{" + "value=" + value + '}';
    }
}
