package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;

public interface IInsuranceService {

    String getInsuranceById(String id);

    void saveInsurance(InsuranceSaveRequestDto insuranceSaveRequestDto);

}
