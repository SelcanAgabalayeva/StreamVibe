package com.selcan.StreamVibe.dto;

import com.selcan.StreamVibe.enums.BillingCycle;
import com.selcan.StreamVibe.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MySubscriptionResponseDto {

    private Integer id;

    private BillingCycle billingCycle;

    private Boolean isTrial;

    private SubscriptionStatus status;

    private LocalDateTime startedAt;

    private LocalDateTime expiresAt;

    private PlanDto plan;
}
