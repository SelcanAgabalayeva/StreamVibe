package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
