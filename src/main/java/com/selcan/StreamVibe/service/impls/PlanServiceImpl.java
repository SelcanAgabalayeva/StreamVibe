package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.PlanDto;
import com.selcan.StreamVibe.repository.PricingPlanRepository;
import com.selcan.StreamVibe.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PricingPlanRepository pricingPlanRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<List<PlanDto>> getPlans(String billing) {

        List<PlanDto> data = pricingPlanRepository.findAll()
                .stream()
                .map(plan -> {
                    PlanDto dto = new PlanDto();

                    dto.setId(plan.getId());
                    dto.setName(plan.getName());
                    dto.setDescription(plan.getDescription());
                    dto.setIsPopular(plan.getIsPopular());

                    dto.setPrice(
                            "yearly".equalsIgnoreCase(billing)
                                    ? plan.getPriceYearly()
                                    : plan.getPriceMonthly()
                    );

                    return dto;
                })
                .toList();

        return ApiResponse.<List<PlanDto>>builder()
                .success(true)
                .message("OK")
                .data(data)
                .build();
    }
}