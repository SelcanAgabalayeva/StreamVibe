package com.selcan.StreamVibe.entity;

import com.selcan.StreamVibe.enums.BillingCycle;
import com.selcan.StreamVibe.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "plan_id",
            nullable = false
    )
    private PricingPlan plan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingCycle billingCycle;

    @Builder.Default
    private Boolean isTrial = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SubscriptionStatus status =
            SubscriptionStatus.ACTIVE;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
