package com.abfsistemas.ecommerce_api.dto;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private boolean isPrimary;
}
