package com.abfsistemas.ecommerce_api.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abfsistemas.ecommerce_api.dto.UserRequestDTO;
import com.abfsistemas.ecommerce_api.dto.UserResponseDTO;
import com.abfsistemas.ecommerce_api.model.Address;
import com.abfsistemas.ecommerce_api.model.Contact;
import com.abfsistemas.ecommerce_api.model.User;
import com.abfsistemas.ecommerce_api.repository.IAddressRepository;
import com.abfsistemas.ecommerce_api.repository.IContactRepository;
import com.abfsistemas.ecommerce_api.repository.IUserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    IUserRepository userRepository;

    @Autowired 
    IContactRepository contactRepository;

    @Autowired
    IAddressRepository addressRepository;

    @PostMapping("/")
    @Transactional
    public ResponseEntity create(@RequestBody UserRequestDTO userRequestDTO) {
        User user = User.fromDTO(userRequestDTO);

        boolean existsUser = userRepository.findByUsername(user.getUsername()) != null;
        if (existsUser) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        
        SCryptPasswordEncoder encode = new SCryptPasswordEncoder(16384, 8, 1, 32, 16);

        String encodedPassword = encode.encode(user.getPassword());

        user.setPassword(encodedPassword);

        if (user.getContacts() != null) {
            for (Contact contact : user.getContacts()) {
                contact.setUser(user); // <- ESSENCIAL
            }
        }

        User userCreated = userRepository.save(user);

        /*if (userRequestDTO.getContacts() != null && !userRequestDTO.getContacts().isEmpty()) {
            Set<Contact> contacts = Contact.fromDTO(userRequestDTO.getContacts());

            contacts.forEach(contact -> {
                contact.setUser(userCreated);
            });

            contactRepository.saveAll(contacts);
        }

        if (userRequestDTO.getAddresses() != null && !userRequestDTO.getAddresses().isEmpty()) {
            Set<Address> addresses = Address.fromDTO(userRequestDTO.getAddresses());

            addresses.forEach(address -> {
                address.setUser(userCreated);
            });

            addressRepository.saveAll(addresses);
        }*/

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated.toDTO());
    }

}
