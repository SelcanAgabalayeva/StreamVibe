package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.ContactRequestDto;
import com.selcan.StreamVibe.dto.ContactResponseDto;

public interface ContactService {
    ContactResponseDto send(ContactRequestDto dto);
}
