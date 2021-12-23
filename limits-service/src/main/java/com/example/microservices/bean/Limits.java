package com.example.microservices.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Limits {
    private int minimum;
    private int maximum;

}
