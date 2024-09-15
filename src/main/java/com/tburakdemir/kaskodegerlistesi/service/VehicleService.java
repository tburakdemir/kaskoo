package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;
import com.tburakdemir.kaskodegerlistesi.mapper.VehicleMapper;
import com.tburakdemir.kaskodegerlistesi.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;


@Service
public class VehicleService implements IVehicleService{

    private final VehicleRepository vehicleRepository;
    private final Logger logger =  Logger.getLogger("VehicleService");
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public String getVehicleByModel(String id){
        return vehicleRepository.findByModel(id).toString();
    }

    @Override
    public ArrayList<Vehicle> getVehicleByBrandAndModel(String brand, String model){
        return vehicleRepository.findByBrandAndModel(brand, model);
    }

    @Override
    public void saveVehicle(VehicleSaveRequestDto vehicleSaveRequestDto){

        logger.info("VehicleService.saveVehicle() method is called");
        Vehicle vehicle = vehicleRepository.save(VehicleMapper.toEntity(vehicleSaveRequestDto));
        logger.info("VehicleService.saveVehicle() method is finished");
        logger.info("VehicleService.saveVehicle() method is finished" + vehicle.getId() + vehicle.getModel());
    }


}
