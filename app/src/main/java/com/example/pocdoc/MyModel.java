package com.example.pocdoc;

public class MyModel {
    String name,num,addess;

    public MyModel(String name, String num, String addess) {
        this.name = name;
        this.num = num;
        this.addess = addess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAddess() {
        return addess;
    }

    public void setAddess(String addess) {
        this.addess = addess;
    }
}
