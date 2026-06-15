package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.GenreDto;
import com.selcan.StreamVibe.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ApiResponse<List<GenreDto>> getGenres(
            @RequestParam(defaultValue = "all") String type) {

        return genreService.getGenres(type);
    }
}
