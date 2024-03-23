package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.mapper.InsuranceMapper;
import com.tburakdemir.kaskodegerlistesi.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class InsuranceService {

    private final Logger logger =  Logger.getLogger("InsuranceService");
    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    public String getInsuranceById(String id){
        return insuranceRepository.findById(Long.parseLong(id)).toString();
    }

    // save method
    public void saveInsurance(InsuranceSaveRequestDto insuranceSaveRequestDto){
        this.logger.info("InsuranceService.saveInsurance() method is called");
        this.insuranceRepository.save(InsuranceMapper.toEntity(insuranceSaveRequestDto));
        this.logger.info("InsuranceService.saveInsurance() method is finished");
    }
}
