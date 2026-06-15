package com.selcan.StreamVibe.service;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.GenreDto;

import java.util.List;

public interface GenreService {
    ApiResponse<List<GenreDto>> getGenres(String type);
}
