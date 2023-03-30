package com.konden.freedom.test;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public String name;
    public int number;


    public Test(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }
}
