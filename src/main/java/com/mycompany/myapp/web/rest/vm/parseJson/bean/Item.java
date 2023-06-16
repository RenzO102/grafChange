package com.mycompany.myapp.web.rest.vm.parseJson.bean;

public class Item {

    private String percentY;
    private String name;
    private int value;
    private int y;
    private int z;

    public Item(String percentY, String name, int value, int y, int z) {
        this.percentY = percentY;
        this.name = name;
        this.value = value;
        this.y = y;
        this.z = z;
    }

    public String getPercentY() {
        return percentY;
    }

    public void setPercentY(String percentY) {
        this.percentY = percentY;
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
