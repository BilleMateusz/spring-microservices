package com.example.microservices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //circuit breaker can be in closed(low failure rate), open(high failure rate) of half-open state
    @CircuitBreaker(name="sample-api", fallbackMethod="hardcodedResponse")
    public String sampleApi() {
        log.info("Sample Api call received");
        ResponseEntity<String> r = new RestTemplate().getForEntity("http://localhost:8080/some-url", String.class);
        return r.getBody();
    }

    private String hardcodedResponse(Exception e) {
        return "fallback-response";
    }

}
