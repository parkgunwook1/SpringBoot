package com.example.shopping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(12);
        helloLombok.setName("park");

        System.out.println("HelloLombok.main" + helloLombok);
    }
}
