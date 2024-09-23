package com.tburakdemir.kaskodegerlistesi.repository;

import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.modelCode = ?1")
    ArrayList<Vehicle> findByModelCode(int modelCode);

    @Query("SELECT v FROM Vehicle v WHERE v.brandCode = ?1 AND v.modelCode = ?2")
    ArrayList<Vehicle> findByBrandAndModelCode(int brandCode, int modelCode);
    List<Vehicle> findByBrand(String brand);

    @Query("SELECT DISTINCT v.brand as brand, v.brandCode as brandCode FROM Vehicle v WHERE v.year = ?1")
    List<Object[]> getBrandsByYear(int year);

    // Set of 
    @Query("SELECT DISTINCT v.model as model, v.modelCode as modelCode FROM Vehicle v WHERE v.brandCode = ?1 AND v.year = ?2")
    List<Object[]> getModelsByBrand(int brandCode, int year);

}
