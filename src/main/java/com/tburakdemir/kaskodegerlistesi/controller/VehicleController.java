package com.tburakdemir.kaskodegerlistesi.controller;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;
import com.tburakdemir.kaskodegerlistesi.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "http://localhost:3000")  // Adjust this to match your frontend URL
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

    @GetMapping("/brands")
    public List<Map<String, Object>> getBrands(@RequestParam(name = "year") int year) {
        return this.vehicleService.getBrandsByYear(year);
    }

    @GetMapping("/models")
    public  List<Map<String, Object>> getModels(@RequestParam(name = "brandCode") int brandCode, @RequestParam(name = "year") int year) {
        return this.vehicleService.getModelsByBrand(brandCode, year);
    }
}
