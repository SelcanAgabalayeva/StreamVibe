package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.User;
import com.selcan.StreamVibe.entity.UserSubscription;
import com.selcan.StreamVibe.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
   }
