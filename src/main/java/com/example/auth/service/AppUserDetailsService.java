package com.example.auth.service;


import com.example.auth.entity.AppUser;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public AppUser signUpUser(AppUser appUser) {

        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Email is already taken");
        }
        String encodedPassword = encodeString(appUser.getPassword());

        appUser.setPassword(encodedPassword);
        appUser.setEnabled(true);
        appUser = userRepository.save(appUser);

        if (!appUser.isEnabled()) {
            throw new IllegalStateException("The user is not enabled yet");
        }

        return appUser;
    }

    private String encodeString(String password) {
        return passwordEncoder.encode(password);
    }

    public AppUser retrieveFromCache(String email) {
        return (AppUser) new CachingUserDetailsService(this).loadUserByUsername(email);
    }
}
