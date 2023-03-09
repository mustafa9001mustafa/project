package com.konden.freedom.model;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public String name;
    public int number;

    public Test() {
    }

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

    public void setNumber(int number) {
        this.number = number;
    }


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("number", number);
        return map;
    }
}
