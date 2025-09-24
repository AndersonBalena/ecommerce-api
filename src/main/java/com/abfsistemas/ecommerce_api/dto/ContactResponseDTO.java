package com.abfsistemas.ecommerce_api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ContactResponseDTO {
    private UUID id;
    private String email;
    private String phoneNumber;
    private String alternativeEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
