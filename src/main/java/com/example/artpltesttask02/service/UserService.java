package com.example.artpltesttask02.service;

import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return userRepository.save(entity);
    }

    public Optional<User> existsUserByUsername(String username) // - Ќе зарегистрированный пользователь должен иметь возможность проверить доступность имени через сервис (валидации).
    {
        return userRepository.existsUserByUsername(username);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
