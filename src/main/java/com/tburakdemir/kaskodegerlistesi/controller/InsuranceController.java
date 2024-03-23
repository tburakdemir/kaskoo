package com.tburakdemir.kaskodegerlistesi.controller;


import com.tburakdemir.kaskodegerlistesi.dto.InsuranceSaveRequestDto;
import com.tburakdemir.kaskodegerlistesi.service.InsuranceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
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

}
