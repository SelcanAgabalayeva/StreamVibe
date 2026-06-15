package com.selcan.StreamVibe.repository;

import com.selcan.StreamVibe.entity.Genre;
import com.selcan.StreamVibe.enums.GenreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    List<Genre> findByType(GenreType type);
}
