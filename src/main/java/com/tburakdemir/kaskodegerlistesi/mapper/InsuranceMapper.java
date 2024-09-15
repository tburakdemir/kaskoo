package com.tburakdemir.kaskodegerlistesi.mapper;

import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.entity.Insurance;
public class InsuranceMapper {

    public static Insurance toEntity(InsuranceSaveRequestDto insuranceSaveRequestDto) {
        Insurance insurance = new Insurance();
        insurance.setBrandCode(insuranceSaveRequestDto.getBrandCode());
        insurance.setModelCode(insuranceSaveRequestDto.getModelCode());
        insurance.setModelYear(insuranceSaveRequestDto.getModelYear());
        insurance.setMonth(insuranceSaveRequestDto.getMonth());
        insurance.setYear(insuranceSaveRequestDto.getYear());
        insurance.setTlPrice(insuranceSaveRequestDto.getTlPrice());
        return insurance;
    }
    public static InsuranceSaveRequestDto toDto(Insurance  insurance) {
        InsuranceSaveRequestDto insuranceSaveRequestDto = new InsuranceSaveRequestDto();
        insuranceSaveRequestDto.setBrandCode(insurance.getBrandCode());
        insuranceSaveRequestDto.setModelCode(insurance.getModelCode());
        insuranceSaveRequestDto.setModelYear(insurance.getModelYear());
        insuranceSaveRequestDto.setMonth(insurance.getMonth());
        insuranceSaveRequestDto.setYear(insurance.getYear());
        insuranceSaveRequestDto.setTlPrice(insurance.getTlPrice());
        return insuranceSaveRequestDto;
    }
}
