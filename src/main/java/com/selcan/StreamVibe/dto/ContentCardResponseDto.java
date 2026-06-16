package com.selcan.StreamVibe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentCardResponseDto {

    private Long id;

    private String title;

    private String posterUrl;

    private Double imdbRating;

    private Double streamvibeRating;

    private Integer releaseYear;

    private String type;
}
