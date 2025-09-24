package com.abfsistemas.ecommerce_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abfsistemas.ecommerce_api.model.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, UUID> {
    
}
