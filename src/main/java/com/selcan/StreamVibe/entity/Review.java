package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    private String reviewerName;

    private String reviewerLocation;

    @Column(nullable = false,precision = 3,scale = 1)
    private BigDecimal rating;

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    private LocalDateTime createdAt;
}
