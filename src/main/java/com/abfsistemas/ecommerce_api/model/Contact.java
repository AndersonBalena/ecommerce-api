package com.abfsistemas.ecommerce_api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abfsistemas.ecommerce_api.dto.ContactRequestDTO;
import com.abfsistemas.ecommerce_api.dto.ContactResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_contact")
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String email;
    private String phoneNumber;
    private String alternativeEmail;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Set<Contact> fromDTO(Set<ContactRequestDTO> contactsDTO) {
        Set<Contact> contacts = new HashSet<Contact>();

        contactsDTO.forEach(dto -> {
            Contact contact = new Contact();
            contact.setEmail(dto.getEmail());
            contact.setPhoneNumber(dto.getPhoneNumber());
            contact.setAlternativeEmail(dto.getAlternativeEmail());
            contacts.add(contact);
        });

        return contacts;
    }

    public ContactResponseDTO toDTO() {
        ContactResponseDTO dto = new ContactResponseDTO();
        dto.setId(this.getId());
        dto.setEmail(this.getEmail());
        dto.setPhoneNumber(this.getPhoneNumber());
        dto.setAlternativeEmail(this.getAlternativeEmail());
        dto.setCreatedAt(this.getCreatedAt());
        dto.setUpdatedAt(this.getUpdatedAt());
        return dto;
    }

}
