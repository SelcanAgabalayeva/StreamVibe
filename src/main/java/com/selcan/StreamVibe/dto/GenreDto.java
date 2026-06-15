package com.selcan.StreamVibe.dto;

import com.selcan.StreamVibe.enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDto {

    private Integer id;

    private String name;

    private String slug;

    private GenreType type;

    private String coverImage1;

    private String coverImage2;

    private String coverImage3;

    private String coverImage4;
}
