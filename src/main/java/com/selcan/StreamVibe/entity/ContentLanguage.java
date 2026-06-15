package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "content_languages")
@Getter
@Setter
public class ContentLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(nullable = false)
    private String language;
}
