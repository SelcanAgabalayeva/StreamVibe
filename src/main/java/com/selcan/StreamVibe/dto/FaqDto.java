package com.selcan.StreamVibe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqDto {

    private Integer id;

    private String question;

    private String answer;

    private Integer orderNumber;
}
