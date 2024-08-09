package com.example.stockservice.service;

import com.example.stockservice.client.StockApiClient;
import com.example.stockservice.model.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockApiClient stockApiClient;

    @Cacheable("quotes")
    public StockQuote getQuote(String symbol) {
        return stockApiClient.getQuote(symbol);
    }

    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        return symbols.stream()
                .map(this::getQuote)
                .collect(Collectors.toList());
    }
}
