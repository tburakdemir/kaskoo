package com.tburakdemir.kaskodegerlistesi.repository;

import com.tburakdemir.kaskodegerlistesi.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.model = ?1")
    ArrayList<Vehicle> findByModel(String model);

    @Query("SELECT v FROM Vehicle v WHERE v.brand = ?1 AND v.model = ?2")
    ArrayList<Vehicle> findByBrandAndModel(String brand, String model);
    List<Vehicle> findByBrand(String brand);

}
