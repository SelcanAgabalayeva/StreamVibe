package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode,Integer> {
    List<Episode> findBySeasonIdOrderByEpisodeNumberAsc(Integer seasonId);
}
