package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.ContentCardResponseDto;
import com.selcan.StreamVibe.dto.GenreDto;
import com.selcan.StreamVibe.dto.HeroResponseDto;
import com.selcan.StreamVibe.dto.TopTenResponseDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ContentService {
    List<HeroResponseDto> getHeroContents();

    List<GenreDto> getGenres(String type);
    List<TopTenResponseDto> getTopTen(String type);
    List<ContentCardResponseDto> getContents(String type, String filter, Integer limit);
}
