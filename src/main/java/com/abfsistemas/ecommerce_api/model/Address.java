package com.abfsistemas.ecommerce_api.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abfsistemas.ecommerce_api.dto.AddressRequestDTO;
import com.abfsistemas.ecommerce_api.dto.AddressResponseDTO;

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
@Table(name = "tb_address")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Set<Address> fromDTO(Set<AddressRequestDTO> addressesDTO) {
        Set<Address> addresses = new HashSet<Address>();

        addressesDTO.forEach(dto -> {
            Address address = new Address();
            address.setAddressLine1(dto.getAddressLine1());
            address.setAddressLine2(dto.getAddressLine2());
            address.setCity(dto.getCity());
            address.setState(dto.getState());
            address.setPostalCode(dto.getPostalCode());
            address.setCountry(dto.getCountry());
            address.setPrimary(dto.isPrimary());
            addresses.add(address);
        });

        return addresses;
    }

    public AddressResponseDTO toDTO() {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setId(this.getId());
        dto.setAddressLine1(this.getAddressLine1());
        dto.setAddressLine2(this.getAddressLine2());
        dto.setCity(this.getCity());
        dto.setState(this.getState());
        dto.setPostalCode(this.getPostalCode());
        dto.setCountry(this.getCountry());
        dto.setPrimary(this.isPrimary());
        dto.setCreatedAt(this.getCreatedAt());
        dto.setUpdatedAt(this.getUpdatedAt());
        return dto;
    }


}
