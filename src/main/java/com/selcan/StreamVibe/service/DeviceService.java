package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    ApiResponse<List<DeviceDto>> getDevices();
}
