package com.example.artpltesttask02.service;

import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private  UserRepository userRepository;
    private  PasswordEncoder encoder;


    public List<User> findAllBy() {
        return userRepository.findAllBy();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void delete(User entity) {
        userRepository.delete(entity);
    }

    public <S extends User> S save(S entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    public Optional<User> existsUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



    public UserRepository getUserRepository() {
        return userRepository;
    }
}
