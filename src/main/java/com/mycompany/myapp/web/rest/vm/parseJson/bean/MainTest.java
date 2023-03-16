package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class MainTest {

    private int main;
    private int value = 0;

    public MainTest(int main) {
        this.main = main;
    }

    public MainTest addValue1() {
        main++;
        return this;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
