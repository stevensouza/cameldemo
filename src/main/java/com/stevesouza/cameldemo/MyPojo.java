package com.stevesouza.cameldemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyPojo {
    private int id;
    private String name;
    private int age;
}
