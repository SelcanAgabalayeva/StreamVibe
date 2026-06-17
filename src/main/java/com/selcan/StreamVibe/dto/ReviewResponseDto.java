package com.selcan.StreamVibe.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private String reviewerName;
    private String reviewerLocation;
    private Double rating;
    private String reviewText;
    private LocalDateTime createdAt;
}
