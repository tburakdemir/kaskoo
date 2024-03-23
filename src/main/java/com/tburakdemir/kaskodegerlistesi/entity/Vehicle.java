package com.tburakdemir.kaskodegerlistesi.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int year;

    @Column
    private String brand;

    @Column
    private String model;

    @OneToMany(mappedBy = "vehicle", targetEntity = Insurance.class)
    List<Insurance> valueByMonthList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Insurance> getValueByMonthList() {
        return valueByMonthList;
    }

    public void setValueByMonthList(List<Insurance> valueByMonthList) {
        this.valueByMonthList = valueByMonthList;
    }
}
