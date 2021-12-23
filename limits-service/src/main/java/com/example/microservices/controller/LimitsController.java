package com.example.microservices.controller;

import com.example.microservices.bean.Limits;
import com.example.microservices.configuration.Configuration;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.Configurable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ObjectInputFilter.Config;

@RequiredArgsConstructor
@RestController
public class LimitsController {

    private final Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
