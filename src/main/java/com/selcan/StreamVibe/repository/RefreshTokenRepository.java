package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.RefreshToken;
import com.selcan.StreamVibe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

    void deleteByToken(String token);
}

