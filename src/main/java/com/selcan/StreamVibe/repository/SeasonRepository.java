package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season,Integer> {
    List<Season> findByContentIdOrderBySeasonNumberAsc(Integer contentId);
}
