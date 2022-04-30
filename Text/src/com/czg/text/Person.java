package com.czg.text;

public class Person {

    private Integer age;
    private String name;
    private String sex;
    private Double sal;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", sal=" + sal +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Person() {
    }

    public Person(Integer age, String name, String sex, Double sal) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.sal = sal;
    }
}
