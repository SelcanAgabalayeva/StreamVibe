package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan_features")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "plan_id",
            nullable = false
    )
    private PricingPlan plan;

    @Column(nullable = false,length = 100)
    private String featureName;

    @Column(nullable = false,length = 255)
    private String featureValue;

    @Column(nullable = false)
    private Integer orderNumber;
}
