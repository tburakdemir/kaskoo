package com.tburakdemir.kaskodegerlistesi.mapper;

import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Insurance;
public class InsuranceMapper {

    public static Insurance toEntity(InsuranceSaveRequestDto insuranceSaveRequestDto) {
        Insurance insurance = new Insurance();
        insurance.setMonth(insuranceSaveRequestDto.getMonth());
        insurance.setYear(insuranceSaveRequestDto.getYear());
        insurance.setVehicle(insuranceSaveRequestDto.getVehicle());
        insurance.setPrice(insuranceSaveRequestDto.getPrice());
        return insurance;
    }
    public static InsuranceSaveRequestDto toDto(Insurance  insurance) {
        InsuranceSaveRequestDto insuranceSaveRequestDto = new InsuranceSaveRequestDto();
        insuranceSaveRequestDto.setMonth(insurance.getMonth());
        insuranceSaveRequestDto.setYear(insurance.getYear());
        insuranceSaveRequestDto.setVehicle(insurance.getVehicle());
        insuranceSaveRequestDto.setPrice(insurance.getPrice());
        return insuranceSaveRequestDto;
    }
}
