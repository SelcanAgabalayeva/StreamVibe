package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.ApiResponse;
import com.selcan.StreamVibe.dto.FaqDto;
import com.selcan.StreamVibe.repository.FaqRepository;
import com.selcan.StreamVibe.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<List<FaqDto>> getFaqs() {

        List<FaqDto> data =
                faqRepository.findAllByOrderByOrderNumberAsc()
                        .stream()
                        .map(faq ->
                                modelMapper.map(faq, FaqDto.class))
                        .toList();

        return ApiResponse.<List<FaqDto>>builder()
                .success(true)
                .message("OK")
                .data(data)
                .build();
    }
}
