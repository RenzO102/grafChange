package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class OptionsWeeks {

    private int value;
    private String year;
    private String Number;

    public OptionsWeeks() {
        this.value = value;
        this.year = year;
        this.Number = Number;
    }

    public static OptionsWeeks week() {
        return new OptionsWeeks();
    }

    public String key() {
        return Number + "/" + year;
    }

    public int getValue() {
        return value;
    }

    public OptionsWeeks setValue(int value) {
        this.value = value;
        return this;
    }

    public String getYear() {
        return year;
    }

    public OptionsWeeks setYear(String year) {
        this.year = year;
        return this;
    }

    public String getNumber() {
        return Number;
    }

    public OptionsWeeks setNumber(String Number) {
        this.Number = Number;
        return this;
    }

    public OptionsWeeks addValue(int name) {
        value = value + name;
        return this;
    }
}
