package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class OptionsMonth {

    private int value;
    private String year;
    private String Number;

    public OptionsMonth() {
        this.value = value;
        this.year = year;
        this.Number = Number();
    }

    public static OptionsMonth month() {
        return new OptionsMonth();
    }

    public String key() {
        return Number + "/" + year;
    }

    public int value() {
        return value;
    }

    public String Number() {
        return Number;
    }

    public String year() {
        return year;
    }

    public int getValue() {
        return value;
    }

    public OptionsMonth setValue(int value) {
        this.value = value;
        return this;
    }

    public String getNumber() {
        return Number;
    }

    public OptionsMonth setNumber(String Number) {
        this.Number = Number;
        return this;
    }

    public String getYear() {
        return year;
    }

    public OptionsMonth setYear(String year) {
        this.year = year;
        return this;
    }

    public OptionsMonth addValue(int name) {
        value = value + name;
        return this;
    }
}
