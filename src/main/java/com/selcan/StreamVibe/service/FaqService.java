package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.FaqDto;

import java.util.List;

public interface FaqService {
    ApiResponse<List<FaqDto>> getFaqs();
}
