package com.selcan.StreamVibe.service.impls;

import com.selcan.StreamVibe.dto.ContactRequestDto;
import com.selcan.StreamVibe.dto.ContactResponseDto;
import com.selcan.StreamVibe.entity.ContactMessage;
import com.selcan.StreamVibe.repository.ContactMessageRepository;
import com.selcan.StreamVibe.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactMessageRepository contactMessageRepository;
    @Override
    public ContactResponseDto send(ContactRequestDto dto){

        ContactMessage contact = ContactMessage.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneCountryCode(dto.getPhoneCountryCode())
                .phoneNumber(dto.getPhoneNumber())
                .message(dto.getMessage())
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        contactMessageRepository.save(contact);

        return ContactResponseDto.builder()
                .id(contact.getId())
                .createdAt(contact.getCreatedAt())
                .build();

    }
}
