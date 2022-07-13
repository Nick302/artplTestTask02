package com.example.auth.service;


import com.example.auth.entity.AppUser;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    public static final int MAX_FAILED_ATTEMPTS = 10;
    private static final long LOCK_TIME_DURATION = 60 * 60 * 1000;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void increaseFailedAttempts(AppUser user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempts(newFailAttempts, user.getEmail());
        System.out.println("User: " + user +" bad enter to account " + newFailAttempts);
    }

    public void resetFailedAttempts(String email) {
        userRepository.updateFailedAttempts(0, email);
    }

    public void lock(AppUser user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        userRepository.save(user);
    }

    public boolean unlockWhenTimeExpired(AppUser user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);
            userRepository.save(user);
            return true;
        }
        return false;
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

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public AppUser findAllByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
}
