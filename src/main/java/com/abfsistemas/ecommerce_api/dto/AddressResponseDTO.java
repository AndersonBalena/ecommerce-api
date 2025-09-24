package com.abfsistemas.ecommerce_api.dto;

import java.util.UUID;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private UUID id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private boolean isPrimary;
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
