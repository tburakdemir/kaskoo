package com.tburakdemir.kaskodegerlistesi.controller;


import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.service.InsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/insurances")
@CrossOrigin(origins = "http://localhost:3000")  
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping("/{id}")
    public String getInsurance(@PathVariable String id) {
        return this.insuranceService.getInsuranceById(id);
    }

    @PostMapping()
    public void saveInsurance(@RequestBody InsuranceSaveRequestDto insuranceSaveRequestDto) {
        insuranceService.saveInsurance(insuranceSaveRequestDto);
    }

    @GetMapping("/{brandCode}/{modelCode}/{year}")
    public List<Map<String, Object>> getInsurance(@PathVariable int brandCode, @PathVariable int modelCode, @PathVariable int year) {
        return insuranceService.getInsuranceByBrandCodeAndModelCodeAndYear(brandCode, modelCode, year);
    }

}
