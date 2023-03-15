package com.mycompany.myapp.web.rest.vm.parseJson;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        return "Data{" + "people=" + items + '}';
    }
}
