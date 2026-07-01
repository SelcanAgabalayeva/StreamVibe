package com.selcan.StreamVibe.dto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Integer id;

    private String username;

    private String email;

    private LocalDateTime createdAt;
}

