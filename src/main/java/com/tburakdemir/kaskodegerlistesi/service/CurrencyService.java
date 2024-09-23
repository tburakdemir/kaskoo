package com.tburakdemir.kaskodegerlistesi.service;

import org.springframework.stereotype.Service;

import com.tburakdemir.kaskodegerlistesi.dto.CurrencySaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.mapper.CurrencyMapper;
import com.tburakdemir.kaskodegerlistesi.repository.CurrencyRepository;

@Service
public class CurrencyService implements ICurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
    
    @Override
    public void saveCurrency(CurrencySaveRequestDto requestDto) {
        
        currencyRepository.save(CurrencyMapper.toEntity(requestDto));
    }
}
