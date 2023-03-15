package com.mycompany.myapp.web.rest.vm.parseJson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
