package com.selcan.StreamVibe.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDetailResponseDto {

    private Long id;
    private String title;
    private String description;

    private String posterUrl;
    private String backgroundUrl;
    private String trailerUrl;

    private String type;

    private Integer releaseYear;

    private Double imdbRating;
    private Double streamvibeRating;

    private List<GenreDto> genres;

    private List<String> languages;

    private List<CastResponseDto> cast;

    private List<PersonResponseDto> directors;

    private List<PersonResponseDto> music;
}