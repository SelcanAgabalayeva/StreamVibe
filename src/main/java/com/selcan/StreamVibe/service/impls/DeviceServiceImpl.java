package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.DeviceDto;
import com.selcan.StreamVibe.repository.DeviceRepository;
import com.selcan.StreamVibe.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<List<DeviceDto>> getDevices() {

        List<DeviceDto> data =
                deviceRepository.findAll()
                        .stream()
                        .map(device ->
                                modelMapper.map(device, DeviceDto.class))
                        .toList();

        return ApiResponse.<List<DeviceDto>>builder()
                .success(true)
                .message("OK")
                .data(data)
                .build();
    }
}
