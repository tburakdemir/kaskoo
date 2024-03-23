package com.tburakdemir.kaskodegerlistesi.dto;

import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;

import java.math.BigDecimal;

public class InsuranceSaveRequestDto {

    private int month;
    private int year;
    private Vehicle vehicle;
    private BigDecimal price;

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
