package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class OptionsWeeks {

    private int value;
    private String year;
    private String weekNumber;

    public OptionsWeeks() {
        this.value = value;
        this.year = year;
        this.weekNumber = weekNumber;
    }

    public static OptionsWeeks week(){
        return new OptionsWeeks();
    }

    public String key() {
        return weekNumber + "/" + year;
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

    public String getWeekNumber() {
        return weekNumber;
    }

    public OptionsWeeks setWeekNumber(String weekNumber) {
        this.weekNumber = weekNumber;
        return this;
    }

    public OptionsWeeks addValue(int name) {
        value = value + name;
        return this;
    }


}
