package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class DateSelecetMethod {

    private String name;

    private int value;

    public DateSelecetMethod(String name) {
        this.name = name;
    }

    public DateSelecetMethod(int value) {
        this.value = value;
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
