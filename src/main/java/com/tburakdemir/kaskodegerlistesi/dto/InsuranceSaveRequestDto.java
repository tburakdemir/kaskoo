package com.tburakdemir.kaskodegerlistesi.dto;

import java.math.BigDecimal;

public class InsuranceSaveRequestDto {
    private int brandCode;
    private int modelCode;
    private int modelYear;
    private int month;
    private int year;
    private BigDecimal tlPrice;

    public int getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(int brandCode) {
        this.brandCode = brandCode;
    }

    public int getModelCode() {
        return modelCode;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getTlPrice() {
        return tlPrice;
    }

    public void setTlPrice(BigDecimal tlPrice) {
        this.tlPrice = tlPrice;
    }
}
