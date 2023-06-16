package com.mycompany.myapp.web.rest.vm.parseJson;

import com.mycompany.myapp.web.rest.vm.parseJson.bean.DateSelecetMethod;

public class WeekData {

    private int value;

    private String year;

    private String weekNumber;

    public WeekData(int value, String year, String weekNumber) {
        this.value = value;
        this.year = year;
        this.weekNumber = weekNumber;
    }

    public String key() {
        return weekNumber + "/" + year;
    }

    public int value() {
        return value;
    }

    public String weekNumber() {
        return weekNumber;
    }

    public String year() {
        return year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(String weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public WeekData addValue(DateSelecetMethod name) {
        value = value + name.getValue();
        return this;
    }
}
