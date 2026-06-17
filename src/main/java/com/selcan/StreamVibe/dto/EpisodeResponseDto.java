package com.selcan.StreamVibe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeResponseDto {

    private Long id;
    private Integer episodeNumber;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Integer durationMinutes;
}
