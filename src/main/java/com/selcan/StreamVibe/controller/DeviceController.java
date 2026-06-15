package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.DeviceDto;
import com.selcan.StreamVibe.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public ApiResponse<List<DeviceDto>> getDevices() {

        return deviceService.getDevices();
    }
}
