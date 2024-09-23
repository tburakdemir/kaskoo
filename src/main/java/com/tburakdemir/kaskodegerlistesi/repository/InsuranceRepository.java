package com.tburakdemir.kaskodegerlistesi.repository;

import com.tburakdemir.kaskodegerlistesi.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    @Query("SELECT DISTINCT i.tlPrice as tlPrice, i.month as month, i.year as year FROM Insurance i WHERE i.brandCode = ?1 AND i.modelCode = ?2 AND i.modelYear = ?3")
    List<Object[]> findByBrandCodeAndModelCodeAndYear(int brandCode, int modelCode, int year);
}
