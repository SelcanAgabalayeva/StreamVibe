package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seasons")
@Getter
@Setter
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    private Integer seasonNumber;

    private Integer episodeCount;

    private String title;
    @OneToMany(mappedBy = "season", fetch = FetchType.LAZY)
    private List<Episode> episodes = new ArrayList<>();
}
