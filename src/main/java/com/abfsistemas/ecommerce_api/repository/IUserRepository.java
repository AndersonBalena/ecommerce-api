package com.abfsistemas.ecommerce_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abfsistemas.ecommerce_api.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    
    

}
