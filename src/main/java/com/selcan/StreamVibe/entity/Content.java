package com.selcan.StreamVibe.entity;

import com.selcan.StreamVibe.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 300)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String posterUrl;

    @Column(nullable = false)
    private String backgroundUrl;

    private String trailerUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    private Integer releaseYear;

    @Column(precision = 3,scale = 1)
    private BigDecimal imdbRating;

    @Column(precision = 3,scale = 1)
    private BigDecimal streamvibeRating;

    private Boolean isTrending = false;

    private Boolean isNewRelease = false;

    private Boolean isMustWatch = false;

    private Boolean isFeatured = false;

    private Integer topTenRank;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private List<ContentGenre> genres = new ArrayList<>();
    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private List<ContentLanguage> languages = new ArrayList<>();
    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private List<ContentPerson> people = new ArrayList<>();
}
