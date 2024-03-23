package com.tburakdemir.kaskodegerlistesi.entity;


import jakarta.persistence.*;


import java.math.BigDecimal;
@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "year", length = 4)
    private int year;

    @Column(name = "month", length= 2)
    private int month;

    @Column(name= "usd_try")
    private BigDecimal usdTry;

    @Column(name = "xau_tryg")
    private BigDecimal xauTryg;

    @Column(name= "minwage_try")
    private BigDecimal minWageTry;

}
