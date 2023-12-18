package com.mycompany.myapp.web.rest.vm.parseJson.bean;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        return "Data{" + "people=" + items + '}';
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
