package com.selcan.StreamVibe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonResponseDto {

    private Long id;
    private Integer seasonNumber;
    private Integer episodeCount;
    private String title;

    private List<EpisodeResponseDto> episodes;
}
