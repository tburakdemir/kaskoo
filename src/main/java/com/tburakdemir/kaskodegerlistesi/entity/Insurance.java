package com.tburakdemir.kaskodegerlistesi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int brandCode;

    @Column
    private int modelCode;

    @Column
    private int modelYear;

    @Column
    private int year;

    @Column
    private int month;

    @Column
    private BigDecimal tlPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getTlPrice() {
        return tlPrice;
    }

    public void setTlPrice(BigDecimal tlPrice) {
        this.tlPrice = tlPrice;
    }
}
