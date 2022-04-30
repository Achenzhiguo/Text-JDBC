package com.czg.text;

public class Student extends Person implements Man {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public Student(){}

    public Student(String name) {
        this.name = name;
    }

    public Student(Integer age, String name, String sex, Double sal, String name1) {
        super(age, name, sex, sal);
        this.name = name1;
    }

    @Override
    public void paly() {
        System.out.println("我可以和你一起玩");
    }
    public void eat(){
        System.out.println("我可以一起吃东西");

    }

}
