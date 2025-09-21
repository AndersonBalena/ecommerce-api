package com.abfsistemas.ecommerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abfsistemas.ecommerce_api.model.User;
import com.abfsistemas.ecommerce_api.repository.IUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody User body) {
        User user = userRepository.findByUsername(body.getUsername());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        SCryptPasswordEncoder encode = new SCryptPasswordEncoder(16384, 8, 1, 32, 16);

        String encodedPassword = encode.encode(body.getPassword());

        body.setPassword(encodedPassword);

        User userCreated = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
