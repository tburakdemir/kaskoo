package com.tburakdemir.kaskodegerlistesi.mapper;

import com.tburakdemir.kaskodegerlistesi.dto.CurrencySaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Currency;

public class CurrencyMapper {
    public static Currency toEntity(CurrencySaveRequestDto currencySaveRequestDto) {
        Currency currency = new Currency();
        currency.setYear(currencySaveRequestDto.getYear());
        currency.setMonth(currencySaveRequestDto.getMonth());
        currency.setUsdTry(currencySaveRequestDto.getUsdTry());
        currency.setXauTryg(currencySaveRequestDto.getXauTryg());
        currency.setMinWageTry(currencySaveRequestDto.getMinWageTry());
        return currency;
    }
    public static CurrencySaveRequestDto toDto(Currency currency) {
        CurrencySaveRequestDto currencySaveRequestDto = new CurrencySaveRequestDto();
        currencySaveRequestDto.setYear(currency.getYear());
        currencySaveRequestDto.setMonth(currency.getMonth());
        currencySaveRequestDto.setUsdTry(currency.getUsdTry());
        currencySaveRequestDto.setXauTryg(currency.getXauTryg());
        currencySaveRequestDto.setMinWageTry(currency.getMinWageTry());
        return currencySaveRequestDto;
    }
}
