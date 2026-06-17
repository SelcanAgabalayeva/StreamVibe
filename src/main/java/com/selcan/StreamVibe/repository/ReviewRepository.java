package com.selcan.StreamVibe.repository;


import com.selcan.StreamVibe.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByContentIdOrderByCreatedAtDesc(Integer contentId);

}
