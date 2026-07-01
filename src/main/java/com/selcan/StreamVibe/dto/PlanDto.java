package com.selcan.StreamVibe.dto;
import com.selcan.StreamVibe.enums.BillingCycle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDto {

    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean isPopular;


    private BillingCycle billingCycle;

    private List<FeatureResponseDto> features;
}
