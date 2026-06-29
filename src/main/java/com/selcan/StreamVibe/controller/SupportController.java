package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.ContactRequestDto;
import com.selcan.StreamVibe.dto.ContactResponseDto;
import com.selcan.StreamVibe.dto.FaqDto;
import com.selcan.StreamVibe.service.ContactService;
import com.selcan.StreamVibe.service.FaqService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/support")
@RequiredArgsConstructor
public class SupportController {

    private final ContactService supportService;
    private final FaqService faqService;

    @PostMapping("/contact")
    public ResponseEntity<?> contact(

            @Valid
            @RequestBody ContactRequestDto dto

    ){

        ContactResponseDto response =
                supportService.send(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(

                        Map.of(
                                "success",true,
                                "message","Your message has been sent successfully",
                                "data",response
                        )

                );

    }
    @GetMapping("/faqs")
    public ApiResponse<List<FaqDto>> getFaqs() {
        return faqService.getFaqs();
    }

}
