package com.mycompany.myapp.web.rest.vm.parseJson;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Item {

    public String percentY;
    public String name;
    public int main;
    public int y;
    public int z;

    public Item(String percentY, String name, int main, int y, int z) {
        this.percentY = percentY;
        this.name = name;
        this.main = main;
        this.y = y;
        this.z = z;
    }
}
