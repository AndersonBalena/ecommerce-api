package com.abfsistemas.ecommerce_api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class UserResponseDTO {
    private UUID userId;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean isActive;

    private Set<ContactResponseDTO> contacts;
    private Set<AddressResponseDTO> addresses;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
