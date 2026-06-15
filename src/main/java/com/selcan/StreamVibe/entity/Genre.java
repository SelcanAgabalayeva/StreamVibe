package com.selcan.StreamVibe.entity;

import com.selcan.StreamVibe.enums.GenreType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,unique = true,length = 50)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenreType type;

    private String coverImage1;
    private String coverImage2;
    private String coverImage3;
    private String coverImage4;
}
