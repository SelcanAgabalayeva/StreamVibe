package com.selcan.StreamVibe.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Subscription billing period"
)
public enum BillingCycle {

    @Schema(description = "Monthly subscription")
    MONTHLY,

    @Schema(description = "Yearly subscription")
    YEARLY
}
