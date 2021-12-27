package com.example.microservices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class CurrencyConversionController {

    private static final boolean USE_WEBCLIENT = true;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{q}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal q
    ) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        final String currencyExchangeUri = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

        CurrencyConversion currencyConversion;
        if (USE_WEBCLIENT) {
            currencyConversion = WebClient.builder()
                    .baseUrl(currencyExchangeUri)
                    .defaultUriVariables(uriVariables)
                    .build()
                    .get()
                    .retrieve()
                    .bodyToMono(CurrencyConversion.class)
                    .block();
        } else {
            ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
                    currencyExchangeUri, CurrencyConversion.class, uriVariables);
            currencyConversion = responseEntity.getBody();
        }

        return new CurrencyConversion(currencyConversion.getId(), from, to, q,
                currencyConversion.getConversionMultiple(),
                q.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment()
        );
    }
}
