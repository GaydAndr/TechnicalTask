package com.example.technicalTask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String state;
    private String address;

}
