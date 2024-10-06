package com.tburakdemir.kaskodegerlistesi.service;

import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.mapper.InsuranceMapper;
import com.tburakdemir.kaskodegerlistesi.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InsuranceService implements IInsuranceService {

    private final Logger logger =  Logger.getLogger("InsuranceService");
    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    @Override
    public String getInsuranceById(String id){
        return insuranceRepository.findById(Long.parseLong(id)).toString();
    }

    @Override
    public void saveInsurance(InsuranceSaveRequestDto insuranceSaveRequestDto){
        this.logger.info("InsuranceService.saveInsurance() method is called");
        this.insuranceRepository.save(InsuranceMapper.toEntity(insuranceSaveRequestDto));
        this.logger.info("InsuranceService.saveInsurance() method is finished");
    }

    public List<Map<String, Object>> getInsuranceByBrandCodeAndModelCodeAndYear(int brandCode, int modelCode, int year) {
        return insuranceRepository.findByBrandCodeAndModelCodeAndYear(brandCode, modelCode, year).stream()
                .map(obj -> Map.of("tlPrice", obj[0], "month", obj[1], "year", obj[2]))
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getInsuranceByBrandCodeAndModelCodeAndYearWithCurrency(int brandCode, int modelCode, int year) {
        return insuranceRepository.findByBrandCodeAndModelCodeAndYearWithCurrency(brandCode, modelCode, year).stream()
                .map(obj -> Map.of("brandCode", obj[0], "modelCode", obj[1], "modelYear", obj[2], "year", obj[3], "month", obj[4], "tlPrice", obj[5], "minWageTry", obj[6], "usdTry", obj[7], "xauTryg", obj[8]))
                .collect(Collectors.toList());
    }
}

