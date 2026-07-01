package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.UserProfileResponseDto;

public interface UserService {
    UserProfileResponseDto profile(Integer userId);
}
