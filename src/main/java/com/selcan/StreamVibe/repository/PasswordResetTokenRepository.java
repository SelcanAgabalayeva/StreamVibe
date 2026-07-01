package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Integer> {
    Optional<PasswordResetToken> findByToken(String token);
}
