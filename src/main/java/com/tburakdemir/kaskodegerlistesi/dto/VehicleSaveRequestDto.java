package com.tburakdemir.kaskodegerlistesi.dto;

import lombok.Data;

@Data
public class VehicleSaveRequestDto {
    private int year;
    private String brand;
    private String model;

}
