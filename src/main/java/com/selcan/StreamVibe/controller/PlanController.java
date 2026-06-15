package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.PlanDto;
import com.selcan.StreamVibe.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public ApiResponse<List<PlanDto>> getPlans(
            @RequestParam(defaultValue = "monthly") String billing) {

        return planService.getPlans(billing);
    }
}
