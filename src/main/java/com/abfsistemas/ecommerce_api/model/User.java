package com.abfsistemas.ecommerce_api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abfsistemas.ecommerce_api.dto.AddressResponseDTO;
import com.abfsistemas.ecommerce_api.dto.ContactResponseDTO;
import com.abfsistemas.ecommerce_api.dto.UserRequestDTO;
import com.abfsistemas.ecommerce_api.dto.UserResponseDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Contact> contacts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Address> addresses;
    
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static User fromDTO(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setActive(dto.isActive());
        user.setPassword(dto.getPassword());
        user.setContacts(Contact.fromDTO(dto.getContacts()));
        user.setAddresses(Address.fromDTO(dto.getAddresses()));
        return user;
    }

    public UserResponseDTO toDTO() {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername(this.getUsername());
        dto.setFirstName(this.getFirstName());
        dto.setLastName(this.getLastName());
        dto.setDateOfBirth(this.getDateOfBirth());
        dto.setActive(this.isActive());
        dto.setCreatedAt(this.getCreatedAt());
        dto.setUpdatedAt(this.getUpdatedAt());
        if (this.getContacts() != null && !this.getContacts().isEmpty()) {
            Set<ContactResponseDTO> contactsDTO = new HashSet<>();
            this.getContacts().forEach(contact -> {
                contactsDTO.add(contact.toDTO());
            });
            dto.setContacts(contactsDTO);
        }
        if (this.getAddresses() != null && !this.getAddresses().isEmpty()) {
            Set<AddressResponseDTO> addressesDTO = new HashSet<>();
            this.getAddresses().forEach(address -> {
                addressesDTO.add(address.toDTO());
            });
            dto.setAddresses(addressesDTO);
        }
        return dto;
    }

}
