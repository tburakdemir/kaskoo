package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.VehicleSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;
import com.tburakdemir.kaskodegerlistesi.mapper.VehicleMapper;
import com.tburakdemir.kaskodegerlistesi.repository.VehicleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Service
public class VehicleService implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final Logger logger = Logger.getLogger("VehicleService");

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public String getVehicleByModelCode(int modelCode) {
        return vehicleRepository.findByModelCode(modelCode).toString();
    }

    @Override
    public ArrayList<Vehicle> getVehicleByBrandAndModelCode(int brandCode, int modelCode) {
        return vehicleRepository.findByBrandAndModelCode(brandCode, modelCode);
    }

    @Override
    public void saveVehicle(VehicleSaveRequestDto vehicleSaveRequestDto) {

        try {
            vehicleRepository.save(VehicleMapper.toEntity(vehicleSaveRequestDto));
        } catch (DataIntegrityViolationException e) {
            logger.warning("Vehicle already exists");
            e.printStackTrace();
        }

    }

    public List<Map<String, Object>> getBrandsByYear(int year) {
        return vehicleRepository.getBrandsByYear(year).stream()
                .map(obj -> Map.of("brand", obj[0], "brandCode", obj[1]))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getModelsByBrand(int brandCode, int year) {
        return vehicleRepository.getModelsByBrand(brandCode, year).stream()
                .map(obj -> Map.of("model", obj[0], "modelCode", obj[1]))
                .collect(Collectors.toList());
    }

}
