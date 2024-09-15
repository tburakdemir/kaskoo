package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;

import java.util.List;

public interface IVehicleService {

    String getVehicleByModel(String model);

    List<Vehicle> getVehicleByBrandAndModel(String brand, String model);

    void saveVehicle(VehicleSaveRequestDto vehicleSaveRequestDto);

}
