package com.selcan.StreamVibe.service.impls;


import com.selcan.StreamVibe.dto.*;
import com.selcan.StreamVibe.entity.*;
import com.selcan.StreamVibe.enums.BillingCycle;
import com.selcan.StreamVibe.enums.SubscriptionStatus;

import com.selcan.StreamVibe.exception.ConflictException;
import com.selcan.StreamVibe.exception.NotFoundException;
import com.selcan.StreamVibe.repository.*;
import com.selcan.StreamVibe.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final PricingPlanRepository pricingPlanRepository;
    private final PlanFeatureRepository planFeatureRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final SubscriptionHistoryRepository subscriptionHistoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<PlanDto> getPlans(BillingCycle billingCycle) {

        List<PricingPlan> plans = pricingPlanRepository.findAll();

        return plans.stream().map(plan -> {

            List<FeatureResponseDto> features =
                    planFeatureRepository.findByPlanIdOrderByOrderNumber(plan.getId())
                            .stream()
                            .map(feature -> FeatureResponseDto.builder()
                                    .featureName(feature.getFeatureName())
                                    .featureValue(feature.getFeatureValue())
                                    .orderNumber(feature.getOrderNumber())
                                    .build())
                            .toList();

            BigDecimal price = billingCycle == BillingCycle.MONTHLY
                    ? plan.getPriceMonthly()
                    : plan.getPriceYearly();

            return PlanDto.builder()
                    .id(plan.getId())
                    .name(plan.getName())
                    .description(plan.getDescription())
                    .price(price)
                    .isPopular(plan.getIsPopular())
                    .billingCycle(billingCycle)
                    .features(features)
                    .build();

        }).toList();
    }

    @Override
    public SubscribeResponseDto subscribe(Integer userId,
                                          SubscribeRequestDto request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("User not found"));


        PricingPlan plan = pricingPlanRepository.findById(request.getPlanId())
                .orElseThrow(() ->
                        new NotFoundException("Plan not found"));

        userSubscriptionRepository
                .findByUserIdAndStatusIn(
                        userId,
                        List.of(
                                SubscriptionStatus.ACTIVE,
                                SubscriptionStatus.TRIAL
                        ))
                .ifPresent(subscription -> {
                    throw new ConflictException(
                            "You already have an active subscription");
                });

        BillingCycle billingCycle = request.getBillingCycle();

        if (billingCycle == null) {
            throw new IllegalArgumentException(
                    "Billing cycle is required"
            );
        }

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime expiresAt;

        if (Boolean.TRUE.equals(request.getIsTrial())) {

            expiresAt = now.plusDays(7);

        } else if (billingCycle == BillingCycle.MONTHLY) {

            expiresAt = now.plusDays(30);

        } else {

            expiresAt = now.plusDays(365);
        }

        SubscriptionStatus status =
                Boolean.TRUE.equals(request.getIsTrial())
                        ? SubscriptionStatus.TRIAL
                        : SubscriptionStatus.ACTIVE;

        UserSubscription subscription =
                UserSubscription.builder()
                        .user(user)
                        .plan(plan)
                        .billingCycle(billingCycle)
                        .isTrial(request.getIsTrial())
                        .status(status)
                        .startedAt(now)
                        .expiresAt(expiresAt)
                        .createdAt(now)
                        .updatedAt(now)
                        .build();

        userSubscriptionRepository.save(subscription);
        PlanDto planDto = PlanDto.builder()
                .id(plan.getId())
                .name(plan.getName())
                .price(
                        billingCycle == BillingCycle.MONTHLY
                                ? plan.getPriceMonthly()
                                : plan.getPriceYearly()
                )
                .build();
        return SubscribeResponseDto.builder()
                .id(subscription.getId())
                .plan(planDto)
                .billingCycle(subscription.getBillingCycle())
                .isTrial(subscription.getIsTrial())
                .status(subscription.getStatus())
                .startedAt(subscription.getStartedAt())
                .expiresAt(subscription.getExpiresAt())
                .build();
    }

    @Override
    public MySubscriptionResponseDto mySubscription(Integer userId) {

        UserSubscription subscription =
                userSubscriptionRepository
                        .findByUserIdAndStatusIn(
                                userId,
                                List.of(
                                        SubscriptionStatus.ACTIVE,
                                        SubscriptionStatus.TRIAL
                                ))
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No active subscription"));


        PricingPlan plan = subscription.getPlan();


        List<FeatureResponseDto> features =
                planFeatureRepository
                        .findByPlanIdOrderByOrderNumber(plan.getId())
                        .stream()
                        .map(feature ->
                                FeatureResponseDto.builder()
                                        .featureName(feature.getFeatureName())
                                        .featureValue(feature.getFeatureValue())
                                        .orderNumber(feature.getOrderNumber())
                                        .build()
                        )
                        .toList();


        PlanDto planDto = PlanDto.builder()
                .id(plan.getId())
                .name(plan.getName())
                .description(plan.getDescription())
                .price(subscription.getBillingCycle() == BillingCycle.MONTHLY
                        ? plan.getPriceMonthly()
                        : plan.getPriceYearly())
                .isPopular(plan.getIsPopular())
                .billingCycle(subscription.getBillingCycle())
                .features(features)
                .build();


        return MySubscriptionResponseDto.builder()
                .id(subscription.getId())
                .billingCycle(subscription.getBillingCycle())
                .isTrial(subscription.getIsTrial())
                .status(subscription.getStatus())
                .startedAt(subscription.getStartedAt())
                .expiresAt(subscription.getExpiresAt())
                .plan(planDto)
                .build();
    }

    @Override
    public void cancel(Integer userId) {

        UserSubscription subscription =
                userSubscriptionRepository
                        .findByUserIdAndStatusIn(
                                userId,
                                List.of(
                                        SubscriptionStatus.ACTIVE,
                                        SubscriptionStatus.TRIAL
                                ))
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "No active subscription"));


        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );


        SubscriptionHistory history =
                SubscriptionHistory.builder()
                        .user(subscription.getUser())
                        .plan(subscription.getPlan())
                        .billingCycle(subscription.getBillingCycle())
                        .isTrial(subscription.getIsTrial())
                        .status(SubscriptionStatus.CANCELLED)
                        .startedAt(subscription.getStartedAt())
                        .expiredAt(subscription.getExpiresAt())
                        .createdAt(LocalDateTime.now())
                        .build();


        subscriptionHistoryRepository.save(history);


        userSubscriptionRepository.delete(subscription);
    }
}
