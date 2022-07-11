package com.example.auth.service;

import com.example.auth.bean.RegistrationRequest;
import com.example.auth.entity.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserDetailsService appUserDetailsService;

    public AppUser register(RegistrationRequest registrationRequest) {
        if (registrationRequest.getEmail().isEmpty()) {
            throw new BadCredentialsException("Email is not corrected");
        }
        return appUserDetailsService.signUpUser(new AppUser(registrationRequest));
    }
}
