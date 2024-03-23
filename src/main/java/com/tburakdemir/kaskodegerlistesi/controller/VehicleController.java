package com.tburakdemir.kaskodegerlistesi.controller;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService  ) {
        this.vehicleService = vehicleService;
    }
    @GetMapping("/{id}")
    public String getVehicle(@PathVariable String id ){
        return this.vehicleService.getVehicleById(id);
    }

    @PostMapping()
    public void saveVehicle(@RequestBody VehicleSaveRequestDto vehicleSaveRequestDto){
        vehicleService.saveVehicle(vehicleSaveRequestDto)  ;
    }
}
