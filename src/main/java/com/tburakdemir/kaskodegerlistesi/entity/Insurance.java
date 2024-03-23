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
}
