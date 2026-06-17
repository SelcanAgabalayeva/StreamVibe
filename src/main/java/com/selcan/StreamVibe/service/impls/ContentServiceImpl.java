package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.*;
import com.selcan.StreamVibe.entity.Content;
import com.selcan.StreamVibe.entity.ContentLanguage;
import com.selcan.StreamVibe.entity.Season;
import com.selcan.StreamVibe.enums.ContentType;
import com.selcan.StreamVibe.enums.GenreType;
import com.selcan.StreamVibe.enums.RoleType;
import com.selcan.StreamVibe.exception.NotFoundException;
import com.selcan.StreamVibe.repository.*;
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
    private final SeasonRepository seasonRepository;
    private final EpisodeRepository episodeRepository;
    private final ReviewRepository reviewRepository;

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

    @Override
    public ContentDetailResponseDto getContentDetail(Integer id) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Content not found with id: " + id
                        ));

        ContentDetailResponseDto dto =
                modelMapper.map(
                        content,
                        ContentDetailResponseDto.class
                );


        dto.setType(content.getType().name());


        if (content.getImdbRating() != null) {
            dto.setImdbRating(
                    content.getImdbRating().doubleValue()
            );
        }


        if (content.getStreamvibeRating() != null) {
            dto.setStreamvibeRating(
                    content.getStreamvibeRating().doubleValue()
            );
        }


        dto.setGenres(
                content.getGenres()
                        .stream()
                        .map(contentGenre ->
                                modelMapper.map(
                                        contentGenre.getGenre(),
                                        GenreDto.class
                                ))
                        .toList()
        );


        dto.setLanguages(
                content.getLanguages()
                        .stream()
                        .map(ContentLanguage::getLanguage)
                        .toList()
        );
        dto.setCast(
                content.getPeople()
                        .stream()
                        .filter(person ->
                                person.getRoleType() == RoleType.ACTOR
                        )
                        .map(cp -> {

                            CastResponseDto castDto =
                                    modelMapper.map(
                                            cp.getPerson(),
                                            CastResponseDto.class
                                    );

                            castDto.setCharacterName(
                                    cp.getCharacterName()
                            );

                            return castDto;

                        })
                        .toList()
        );
        dto.setDirectors(
                content.getPeople()
                        .stream()
                        .filter(person ->
                                person.getRoleType() == RoleType.DIRECTOR
                        )
                        .map(cp ->
                                modelMapper.map(
                                        cp.getPerson(),
                                        PersonResponseDto.class
                                ))
                        .toList()
        );
        dto.setMusic(
                content.getPeople()
                        .stream()
                        .filter(person ->
                                person.getRoleType() == RoleType.MUSIC
                        )
                        .map(cp ->
                                modelMapper.map(
                                        cp.getPerson(),
                                        PersonResponseDto.class
                                ))
                        .toList()
        );


        return dto;
    }
    @Override
    public List<SeasonResponseDto> getSeasons(Integer contentId) {

        if (!contentRepository.existsById(contentId)) {
            throw new NotFoundException("Content not found");
        }

        List<Season> seasons =
                seasonRepository.findByContentIdOrderBySeasonNumberAsc(contentId);

        return seasons.stream()
                .map(season -> {

                    SeasonResponseDto seasonDto =
                            modelMapper.map(season, SeasonResponseDto.class);

                    List<EpisodeResponseDto> episodes =
                            episodeRepository
                                    .findBySeasonIdOrderByEpisodeNumberAsc(season.getId())
                                    .stream()
                                    .map(episode ->
                                            modelMapper.map(
                                                    episode,
                                                    EpisodeResponseDto.class
                                            ))
                                    .toList();

                    seasonDto.setEpisodes(episodes);

                    return seasonDto;
                })
                .toList();
    }
    @Override
    public List<ReviewResponseDto> getReviews(Integer id) {


        if (!contentRepository.existsById(id)) {
            throw new NotFoundException(
                    "Content not found with id: " + id
            );
        }


        return reviewRepository
                .findByContentIdOrderByCreatedAtDesc(id)
                .stream()
                .map(review ->
                        modelMapper.map(
                                review,
                                ReviewResponseDto.class
                        ))
                .toList();
    }
}
