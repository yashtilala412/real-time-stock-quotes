package com.example.stockservice.controller;

import com.example.stockservice.model.StockQuote;
import com.example.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{symbol}")
    public ResponseEntity<StockQuote> getQuoteBySymbol(@PathVariable String symbol) {
        StockQuote quote = stockService.getQuote(symbol);
        return ResponseEntity.ok(quote);
    }

    @GetMapping
    public ResponseEntity<List<StockQuote>> getBatchQuotes(@RequestParam List<String> symbols) {
        List<StockQuote> quotes = stockService.getBatchQuotes(symbols);
        return ResponseEntity.ok(quotes);
    }
}
