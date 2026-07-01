package com.selcan.StreamVibe.dto;

import com.selcan.StreamVibe.enums.BillingCycle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeRequestDto {

    @Schema(
            description = "Selected pricing plan id",
            example = "1"
    )
    @NotNull
    private Integer planId;



    @Schema(
            description = "Billing cycle",
            implementation = BillingCycle.class
    )
    private BillingCycle billingCycle;
    @Schema(
            description = "Activate free trial",
            example = "true"
    )
    private Boolean isTrial;
}
