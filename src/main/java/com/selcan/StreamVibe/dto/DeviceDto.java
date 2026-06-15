package com.selcan.StreamVibe.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDto {

    private Integer id;

    private String name;

    private String description;

    private String iconName;
}