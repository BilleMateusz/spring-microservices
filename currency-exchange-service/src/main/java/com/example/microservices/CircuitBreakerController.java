package com.example.microservices;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //circuit breaker can be in closed(low failure rate), open(high failure rate) of half-open state
//    @CircuitBreaker(name="sample-api", fallbackMethod="hardcodedResponse")
    @RateLimiter(name = "default", fallbackMethod = "rateLimiterFallback")
    @Bulkhead(name = "default")
    public String sampleApi() {
        log.info("Sample Api call received");
        return "sample-api";
    }

    private String hardcodedResponse(Exception e) {
        return "fallback-response";
    }

    private String rateLimiterFallback(Exception e) {
        log.info(e.getMessage());
        return "rate limiter fallback";
    }

}
