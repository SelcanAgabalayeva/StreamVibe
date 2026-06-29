package com.selcan.StreamVibe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneCountryCode;

    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Builder.Default
    private Boolean isRead = false;

    private LocalDateTime createdAt;
}
