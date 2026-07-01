package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.SubscribeRequestDto;
import com.selcan.StreamVibe.dto.SubscribeResponseDto;
import com.selcan.StreamVibe.entity.User;
import com.selcan.StreamVibe.enums.BillingCycle;
import com.selcan.StreamVibe.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @GetMapping("/plans")
    public ResponseEntity<?> getPlans(
            @RequestParam(defaultValue = "MONTHLY") BillingCycle billing){

        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "data",
                        subscriptionService.getPlans(billing)
                )
        );
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            Authentication authentication,
            @Valid @RequestBody SubscribeRequestDto request
    ){

        User user =
                (User) authentication.getPrincipal();


        Integer userId =
                user.getId();


        SubscribeResponseDto response =
                subscriptionService.subscribe(
                        userId,
                        request
                );


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        Map.of(
                                "success", true,
                                "message",
                                "Subscription activated successfully",
                                "data",
                                response
                        )
                );
    }
    @GetMapping("/my")
    public ResponseEntity<?> mySubscription(
            Authentication authentication
    ){

        User user = (User) authentication.getPrincipal();


        Integer userId = user.getId();


        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "data",
                        subscriptionService.mySubscription(userId)
                )
        );
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<?> cancel(
            Authentication authentication
    ){

        User user =
                (User) authentication.getPrincipal();


        Integer userId =
                user.getId();


        subscriptionService.cancel(userId);


        return ResponseEntity.ok(
                Map.of(
                        "success", true,
                        "message",
                        "Subscription cancelled successfully"
                )
        );
    }

}
