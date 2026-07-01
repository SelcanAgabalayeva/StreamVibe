package com.selcan.StreamVibe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureResponseDto {

    private String featureName;
    private String featureValue;
    private Integer orderNumber;
}
