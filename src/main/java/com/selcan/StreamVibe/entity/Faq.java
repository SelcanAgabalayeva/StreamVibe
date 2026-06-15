package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "faqs")
@Getter
@Setter
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    private Integer orderNumber;
}
