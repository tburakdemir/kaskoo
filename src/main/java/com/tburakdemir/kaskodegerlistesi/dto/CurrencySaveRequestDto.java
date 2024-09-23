package com.tburakdemir.kaskodegerlistesi.dto;

import java.math.BigDecimal;

public class CurrencySaveRequestDto {
    private int year;
    private int month;
    private BigDecimal usdTry;
    private BigDecimal xauTryg;
    private BigDecimal minWageTry;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getUsdTry() {
        return usdTry;
    }

    public void setUsdTry(BigDecimal usdTry) {
        this.usdTry = usdTry;
    }

    public BigDecimal getXauTryg() {
        return xauTryg;
    }
    
    public void setXauTryg(BigDecimal xauTryg) {
        this.xauTryg = xauTryg;
    }

    public BigDecimal getMinWageTry() {
        return minWageTry;
    }

    public void setMinWageTry(BigDecimal minWageTry) {
        this.minWageTry = minWageTry;
    }
    
}
