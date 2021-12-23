package com.example.microservices.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Getter
@Setter
@ConfigurationProperties("limits-service")
public class Configuration {
    private int minimum;
    private int maximum;
}
