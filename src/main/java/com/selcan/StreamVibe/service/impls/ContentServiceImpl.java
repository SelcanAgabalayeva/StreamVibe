package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.ContentCardResponseDto;
import com.selcan.StreamVibe.dto.GenreDto;
import com.selcan.StreamVibe.dto.HeroResponseDto;
import com.selcan.StreamVibe.dto.TopTenResponseDto;
import com.selcan.StreamVibe.entity.Content;
import com.selcan.StreamVibe.enums.ContentType;
import com.selcan.StreamVibe.enums.GenreType;
import com.selcan.StreamVibe.repository.ContentRepository;
import com.selcan.StreamVibe.repository.GenreRepository;
import com.selcan.StreamVibe.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;

    @Override
    public List<HeroResponseDto> getHeroContents() {

        return contentRepository.findByIsFeaturedTrue()
                .stream()
                .map(content ->
                        modelMapper.map(
                                content,
                                HeroResponseDto.class
                        ))
                .toList();
    }

    @Override
    public List<GenreDto> getGenres(String type) {

        GenreType genreType = parseType(type);

        return genreRepository.findByType(genreType)
                .stream()
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .toList();
    }


    private GenreType parseType(String type) {
        try {
            return GenreType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Type must be movie or show");
        }
    }

    @Override
    public List<TopTenResponseDto> getTopTen(String type) {

        ContentType contentType = parseContentType(type);

        return contentRepository.getTopTen(contentType)
                .stream()
                .map(content ->
                        modelMapper.map(
                                content,
                                TopTenResponseDto.class
                        ))
                .toList();
    }

    @Override
    public List<ContentCardResponseDto> getContents(
            String type,
            String filter,
            Integer limit
    ) {

        ContentType contentType = parseContentType(type);

        Pageable pageable =
                PageRequest.of(0, limit);

        Page<Content> contents;

        switch (filter) {

            case "trending" ->
                    contents =
                            contentRepository
                                    .findByTypeAndIsTrendingTrue(
                                            contentType,
                                            pageable
                                    );

            case "new-release" ->
                    contents =
                            contentRepository
                                    .findByTypeAndIsNewReleaseTrue(
                                            contentType,
                                            pageable
                                    );

            case "must-watch" ->
                    contents =
                            contentRepository
                                    .findByTypeAndIsMustWatchTrue(
                                            contentType,
                                            pageable
                                    );

            default ->
                    throw new IllegalArgumentException(
                            "Filter must be: trending, new-release or must-watch"
                    );
        }

        return contents.getContent()
                .stream()
                .map(content ->
                        modelMapper.map(
                                content,
                                ContentCardResponseDto.class
                        ))
                .toList();
    }

    private ContentType parseContentType(String type) {
        try {
            return ContentType.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Type must be movie or show");
        }
    }
}
