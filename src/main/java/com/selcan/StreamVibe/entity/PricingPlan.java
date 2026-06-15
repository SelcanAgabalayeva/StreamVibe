package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pricing_plans")
@Getter
@Setter
public class PricingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal priceMonthly;

    private BigDecimal priceYearly;

    private Boolean isPopular = false;
}
