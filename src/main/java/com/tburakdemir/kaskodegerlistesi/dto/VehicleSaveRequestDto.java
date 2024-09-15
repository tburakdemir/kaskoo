package com.tburakdemir.kaskodegerlistesi.dto;

public class VehicleSaveRequestDto {

    private int brandCode;
    private int modelCode;
    private int year;
    private String brand;
    private String model;

    public int getBrandCode() {return brandCode;}

    public void setBrandCode(int brandCode) {this.brandCode = brandCode;}

    public int getModelCode() {return modelCode;}

    public void setModelCode(int modelCode) {this.modelCode = modelCode;}

    public int getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
