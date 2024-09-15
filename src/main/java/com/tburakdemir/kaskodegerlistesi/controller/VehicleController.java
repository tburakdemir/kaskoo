package com.tburakdemir.kaskodegerlistesi.controller;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;
import com.tburakdemir.kaskodegerlistesi.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService  ) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("/{modelCode}")
    public String getVehicle(@PathVariable int modelCode ){
        return this.vehicleService.getVehicleByModelCode(modelCode);
    }

    @GetMapping("/{brand}/{model}")
    public ArrayList<Vehicle> getVehicle(@PathVariable int brandCode, @PathVariable int modelCode ){
        return this.vehicleService.getVehicleByBrandAndModelCode(brandCode, modelCode);
    }

    @PostMapping()
    public void saveVehicle(@RequestBody VehicleSaveRequestDto vehicleSaveRequestDto){
        vehicleService.saveVehicle(vehicleSaveRequestDto)  ;
    }
}
