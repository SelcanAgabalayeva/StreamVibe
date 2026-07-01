package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.*;

public interface AuthService {
    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);
    AuthResponseDto refreshToken(RefreshTokenRequestDto request);

    void logout(RefreshTokenRequestDto request);

    UserResponseDto me();
}
