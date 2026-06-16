package com.selcan.StreamVibe.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopTenResponseDto {

    private Long id;

    private String title;

    private String posterUrl;

    private Integer topTenRank;

}
