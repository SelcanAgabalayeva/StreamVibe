package com.selcan.StreamVibe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogoutResponseDto {

    private boolean success;
    private String message;
}
