package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;

import java.util.List;

public interface IVehicleService {

    String getVehicleByModelCode(int modelCode);

    List<Vehicle> getVehicleByBrandAndModelCode(int brandCode, int modelCode);

    void saveVehicle(VehicleSaveRequestDto vehicleSaveRequestDto);

}
