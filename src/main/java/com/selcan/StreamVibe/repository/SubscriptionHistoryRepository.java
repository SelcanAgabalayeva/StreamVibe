package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.SubscriptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory,Integer> {
}
