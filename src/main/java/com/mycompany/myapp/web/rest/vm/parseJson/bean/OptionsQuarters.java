package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class OptionsQuarters {

    private int value;

    private String year;

    private String quarterNumber;

    public OptionsQuarters(int value, String year, String weekNumber) {
        this.value = value;
        this.year = year;
        this.quarterNumber = weekNumber;
    }

    public String key() {
        return quarterNumber + "/" + year;
    }

    public OptionsQuarters() {
        this.value = value;
        this.year = year;
        this.quarterNumber = quarterNumber;
    }

    public static OptionsQuarters quarter(){
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

    public String getQuarterNumber() {
        return quarterNumber;
    }

    public OptionsQuarters setQuarterNumber(String quarterNumber) {
        this.quarterNumber = quarterNumber;
        return this;
    }

    public OptionsQuarters addValue(int name) {
        value = value + name;
        return this;
    }
}
