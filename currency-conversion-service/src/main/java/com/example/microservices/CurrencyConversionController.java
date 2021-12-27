package com.example.microservices;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{q}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal q
    ) {
        return new CurrencyConversion(10001L, from, to, q,
                BigDecimal.ONE,
                BigDecimal.ONE,
                ""
        );
    }
}
