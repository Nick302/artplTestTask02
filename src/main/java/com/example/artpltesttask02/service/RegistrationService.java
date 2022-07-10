package com.example.artpltesttask02.service;


import com.example.artpltesttask02.bean.RegistrationRequest;
import com.example.artpltesttask02.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserDetailsServiceImpl userDetailsService;

    public User register(RegistrationRequest registrationRequest) {
        if(registrationRequest.getUsername().isEmpty()){
            throw new BadCredentialsException("Username is not corrected");
        }
        return userDetailsService.signUpUser(new User(registrationRequest));
    }
}
