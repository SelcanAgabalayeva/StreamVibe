package com.selcan.StreamVibe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone country code is required")
    private String phoneCountryCode;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Phone number must contain only digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Message is required")
    @Size(
            min = 20,
            message = "Message must be at least 20 characters"
    )
    private String message;

}
