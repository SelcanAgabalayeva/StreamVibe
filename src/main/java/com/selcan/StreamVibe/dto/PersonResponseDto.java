package com.selcan.StreamVibe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDto {

    private Long id;
    private String name;
    private String avatarUrl;
    private String nationality;
}
