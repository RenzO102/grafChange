package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class OptionsQuarters {

    private int value;

    private String year;

    private String Number;

    public OptionsQuarters(int value, String year, String Number) {
        this.value = value;
        this.year = year;
        this.Number = Number;
    }

    public String key() {
        return Number + "/" + year;
    }

    public OptionsQuarters() {
        this.value = value;
        this.year = year;
        this.Number = Number;
    }

    public static OptionsQuarters quarter() {
        return new OptionsQuarters();
    }

    public int getValue() {
        return value;
    }

    public OptionsQuarters setValue(int value) {
        this.value = value;
        return this;
    }

    public String getYear() {
        return year;
    }

    public OptionsQuarters setYear(String year) {
        this.year = year;
        return this;
    }

    public String getNumber() {
        return Number;
    }

    public OptionsQuarters setNumber(String Number) {
        this.Number = Number;
        return this;
    }

    public OptionsQuarters addValue(int name) {
        value = value + name;
        return this;
    }
}
