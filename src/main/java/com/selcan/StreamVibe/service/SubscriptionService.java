package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.MySubscriptionResponseDto;
import com.selcan.StreamVibe.dto.PlanDto;
import com.selcan.StreamVibe.dto.SubscribeRequestDto;
import com.selcan.StreamVibe.dto.SubscribeResponseDto;
import com.selcan.StreamVibe.enums.BillingCycle;

import java.util.List;

public interface SubscriptionService {

    List<PlanDto> getPlans(BillingCycle billingCycle);

    SubscribeResponseDto subscribe(
            Integer userId,
            SubscribeRequestDto request
    );

    MySubscriptionResponseDto mySubscription(Integer userId);

    void cancel(Integer userId);
}
