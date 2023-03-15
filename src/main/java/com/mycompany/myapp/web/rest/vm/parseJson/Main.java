package com.mycompany.myapp.web.rest.vm.parseJson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Main {

    private int main;
    private int value = 0;

    public Main(int main) {
        this.main = main;
    }

    public Main addValue1() {
        main++;
        return this;
    }
}
