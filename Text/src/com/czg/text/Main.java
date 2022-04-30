package com.czg.text;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        Student student = new Student();
        student.setAge(18);
        student.setName("xiaoming");
        student.setSex("男");
        student.setSal(2000.12);
        System.out.println(student);
        student.paly();
        String name = student.getName();
        Integer age = student.getAge();
//        Scanner s = new Scanner(System.in);
//        Integer age1 = s.nextInt();

        Man man = new Student();
        Student student1 = new Student();
        Student student2 = new Student();


    }

}
