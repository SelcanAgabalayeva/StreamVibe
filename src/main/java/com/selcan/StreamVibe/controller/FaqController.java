package com.selcan.StreamVibe.controller;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.FaqDto;
import com.selcan.StreamVibe.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @GetMapping
    public ApiResponse<List<FaqDto>> getFaqs() {

        return faqService.getFaqs();
    }
}
