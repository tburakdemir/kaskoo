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
    @GetMapping("/{id}")
    public String getVehicle(@PathVariable String model ){
        return this.vehicleService.getVehicleByModel(model);
    }

    @GetMapping("/{brand}/{model}")
    public ArrayList<Vehicle> getVehicle(@PathVariable String brand, @PathVariable String model ){
        return this.vehicleService.getVehicleByBrandAndModel(brand, model);
    }

    @PostMapping()
    public void saveVehicle(@RequestBody VehicleSaveRequestDto vehicleSaveRequestDto){
        vehicleService.saveVehicle(vehicleSaveRequestDto)  ;
    }
}
