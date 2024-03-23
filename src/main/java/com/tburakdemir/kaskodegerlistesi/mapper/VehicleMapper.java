package com.tburakdemir.kaskodegerlistesi.mapper;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;

public class VehicleMapper {
    public static Vehicle toEntity(VehicleSaveRequestDto vehicleSaveRequestDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleSaveRequestDto.getBrand());
        vehicle.setModel(vehicleSaveRequestDto.getModel());
        vehicle.setYear(vehicleSaveRequestDto.getYear());
        return vehicle;
    }
    public static VehicleSaveRequestDto toDto(Vehicle vehicle) {
        VehicleSaveRequestDto vehicleSaveRequestDto = new VehicleSaveRequestDto();
        vehicleSaveRequestDto.setBrand(vehicle.getBrand());
        vehicleSaveRequestDto.setModel(vehicle.getModel());
        vehicleSaveRequestDto.setYear(vehicle.getYear());
        return vehicleSaveRequestDto;
    }
}
