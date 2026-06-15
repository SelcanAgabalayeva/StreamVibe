package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.GenreDto;
import com.selcan.StreamVibe.entity.Genre;
import com.selcan.StreamVibe.enums.GenreType;
import com.selcan.StreamVibe.repository.GenreRepository;
import com.selcan.StreamVibe.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<List<GenreDto>> getGenres(String type) {

        List<Genre> genres;

        if ("movie".equalsIgnoreCase(type)) {
            genres = genreRepository.findByType(GenreType.MOVIE);

        } else if ("show".equalsIgnoreCase(type)) {
            genres = genreRepository.findByType(GenreType.SHOW);

        } else {
            genres = genreRepository.findAll();
        }

        List<GenreDto> data =
                genres.stream()
                        .map(genre ->
                                modelMapper.map(genre, GenreDto.class))
                        .toList();

        return ApiResponse.<List<GenreDto>>builder()
                .success(true)
                .message("OK")
                .data(data)
                .build();
    }
}
