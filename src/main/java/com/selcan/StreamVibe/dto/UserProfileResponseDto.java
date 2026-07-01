package com.selcan.StreamVibe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {

    private UserResponseDto user;
    @Schema(
            description = "Current active subscription. Null if user has no subscription.",
            nullable = true
    )
    private SubscribeResponseDto subscription;
}
