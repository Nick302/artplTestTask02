package com.example.auth.service;


import com.example.auth.entity.AppUser;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(AppUser entity) {
        userRepository.delete(entity);
    }

    public <S extends AppUser> S save(S entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByEmail(username);
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
}
