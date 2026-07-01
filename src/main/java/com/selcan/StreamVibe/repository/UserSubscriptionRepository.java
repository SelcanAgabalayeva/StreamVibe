package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.UserSubscription;
import com.selcan.StreamVibe.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription,Integer> {

    Optional<UserSubscription> findByUserIdAndStatusIn(Integer userId, List<SubscriptionStatus> statuses);

    Optional<UserSubscription> findByUserId(Integer userId);
}
