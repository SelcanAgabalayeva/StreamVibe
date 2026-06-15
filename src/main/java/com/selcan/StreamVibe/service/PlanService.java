package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.PlanDto;

import java.util.List;

public interface PlanService {
    ApiResponse<List<PlanDto>> getPlans(String billing);
}
