package com.abfsistemas.ecommerce_api.dto;

import lombok.Data;

@Data
public class ContactRequestDTO {
    private String email;
    private String phoneNumber;
    private String alternativeEmail;
}
