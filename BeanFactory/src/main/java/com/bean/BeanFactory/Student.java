package com.bean.BeanFactory;

public class Student {
    private String name;
    private String age;

    public Student(String name, String age){
        this.name=name;
        this.age=age;
    }

    public String toString(){
        return "Student {name = '"+ name + "', age = '" + age + "'}";
    }
}
