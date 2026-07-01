package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.PlanFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanFeatureRepository extends JpaRepository<PlanFeature,Integer> {
    List<PlanFeature> findByPlanIdOrderByOrderNumber(Integer planId);
}
