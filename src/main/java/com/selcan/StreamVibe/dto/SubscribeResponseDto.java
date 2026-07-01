package com.selcan.StreamVibe.dto;

import com.selcan.StreamVibe.enums.BillingCycle;
import com.selcan.StreamVibe.enums.SubscriptionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SubscribeResponseDto {

    private Integer id;

    private PlanDto plan;

    private BillingCycle billingCycle;

    private Boolean isTrial;

    private SubscriptionStatus status;

    private LocalDateTime startedAt;

    private LocalDateTime expiresAt;
}
