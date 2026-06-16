package com.selcan.StreamVibe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeroResponseDto {

    private Long id;

    private String title;

    private String description;

    private String backgroundUrl;

    private String trailerUrl;

    private String type;
}
