package com.selcan.StreamVibe.entity;

import com.selcan.StreamVibe.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "content_people")
@Getter
@Setter
public class ContentPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String characterName;
}
