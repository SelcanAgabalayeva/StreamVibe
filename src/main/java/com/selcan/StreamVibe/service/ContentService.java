package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.*;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ContentService {
    List<HeroResponseDto> getHeroContents();

    List<GenreDto> getGenres(String type);
    List<TopTenResponseDto> getTopTen(String type);
    List<ContentCardResponseDto> getContents(String type, String filter, Integer limit);

    ContentDetailResponseDto getContentDetail(Integer id);

    List<SeasonResponseDto> getSeasons(Integer contentId);
    List<ReviewResponseDto> getReviews(Integer id);

}
