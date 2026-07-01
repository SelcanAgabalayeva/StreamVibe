package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.*;
import com.selcan.StreamVibe.entity.PricingPlan;
import com.selcan.StreamVibe.entity.User;
import com.selcan.StreamVibe.entity.UserSubscription;
import com.selcan.StreamVibe.enums.BillingCycle;
import com.selcan.StreamVibe.enums.SubscriptionStatus;
import com.selcan.StreamVibe.exception.NotFoundException;
import com.selcan.StreamVibe.repository.UserRepository;
import com.selcan.StreamVibe.repository.UserSubscriptionRepository;
import com.selcan.StreamVibe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    @Override
    public UserProfileResponseDto profile(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("User not found"));

        UserResponseDto userDto = UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        Optional<UserSubscription> optionalSubscription =
                userSubscriptionRepository.findByUserIdAndStatusIn(
                        userId,
                        List.of(
                                SubscriptionStatus.ACTIVE,
                                SubscriptionStatus.TRIAL
                        ));

        SubscribeResponseDto subscriptionDto = null;

        if (optionalSubscription.isPresent()) {

            UserSubscription subscription =
                    optionalSubscription.get();

            PricingPlan plan =
                    subscription.getPlan();

          PlanDto planDto =
                    PlanDto.builder()
                            .id(plan.getId())
                            .name(plan.getName())
                            .price(
                                    subscription.getBillingCycle()
                                            == BillingCycle.MONTHLY
                                            ? plan.getPriceMonthly()
                                            : plan.getPriceYearly()
                            )
                            .build();

            subscriptionDto =
                    SubscribeResponseDto.builder()
                            .id(subscription.getId())
                            .billingCycle(subscription.getBillingCycle())
                            .isTrial(subscription.getIsTrial())
                            .status(subscription.getStatus())
                            .startedAt(subscription.getStartedAt())
                            .expiresAt(subscription.getExpiresAt())
                            .plan(planDto)
                            .build();
        }

        return UserProfileResponseDto.builder()
                .user(userDto)
                .subscription(subscriptionDto)
                .build();
    }
}
