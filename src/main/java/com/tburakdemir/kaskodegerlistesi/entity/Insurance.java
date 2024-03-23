package com.tburakdemir.kaskodegerlistesi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "year", length = 4)
    private int year;

    @Column(name = "month", length= 2)
    private int month;

    @ManyToOne(targetEntity = Vehicle.class)
    @JoinColumn(name = "vehicle_model_id")
    private Vehicle vehicle;

    @Column(name = "try_price")
    private BigDecimal price;

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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
