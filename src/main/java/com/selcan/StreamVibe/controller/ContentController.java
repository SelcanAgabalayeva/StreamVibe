package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.ContentCardResponseDto;
import com.selcan.StreamVibe.dto.GenreDto;
import com.selcan.StreamVibe.dto.HeroResponseDto;
import com.selcan.StreamVibe.dto.TopTenResponseDto;
import com.selcan.StreamVibe.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/hero")
    public ResponseEntity<List<HeroResponseDto>> hero() {
        return ResponseEntity.ok(
                contentService.getHeroContents()
        );
    }
    @GetMapping("/genres")
    public ResponseEntity<List<GenreDto>> genres(
            @RequestParam String type
    ) {
        return ResponseEntity.ok(
                contentService.getGenres(type)
        );
    }

    @GetMapping("/top-ten")
    public ResponseEntity<List<TopTenResponseDto>> topTen(
            @RequestParam String type
    ) {
        return ResponseEntity.ok(
                contentService.getTopTen(type)
        );
    }

    @GetMapping
    public ResponseEntity<List<ContentCardResponseDto>> getContents(
            @RequestParam String type,
            @RequestParam String filter,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return ResponseEntity.ok(
                contentService.getContents(
                        type,
                        filter,
                        limit
                )
        );
    }
}
