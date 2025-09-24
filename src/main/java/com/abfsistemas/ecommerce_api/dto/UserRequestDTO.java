package com.abfsistemas.ecommerce_api.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private String password;

    private Set<ContactRequestDTO> contacts;
    private Set<AddressRequestDTO> addresses;
}
