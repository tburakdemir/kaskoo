package com.tburakdemir.kaskodegerlistesi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tburakdemir.kaskodegerlistesi.dto.CurrencySaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.service.CurrencyService;

@RestController
@RequestMapping("/currencies")
@CrossOrigin(origins = "http://localhost:3000")  
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping
    public ResponseEntity<String> saveCurrency(@RequestBody CurrencySaveRequestDto requestDto) {
        currencyService.saveCurrency(requestDto);
        return ResponseEntity.ok("Currency saved successfully");
    }
    
}
