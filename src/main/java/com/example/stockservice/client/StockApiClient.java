package com.example.stockservice.client;

import com.example.stockservice.model.StockQuote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class StockApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "W5Z13V3ABD8K9BCQ";
    private final String baseUrl = "https://www.alphavantage.co/query";

    public StockQuote getQuote(String symbol) {
        String url = String.format("%s?function=GLOBAL_QUOTE&symbol=%s&apikey=%s", baseUrl, symbol, apiKey);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, String> quoteData = (Map<String, String>) response.get("Global Quote");

        return new StockQuote(
            quoteData.get("01. symbol"),
            Double.parseDouble(quoteData.get("05. price")),
            Double.parseDouble(quoteData.get("09. change")),
            Double.parseDouble(quoteData.get("10. change percent").replace("%", "")),
            LocalDateTime.now()
        );
    }
}
