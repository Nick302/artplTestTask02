package com.example.auth.controller;

import com.example.auth.bean.ErrorResponse;
import com.example.auth.bean.LoginRequest;
import com.example.auth.bean.RegistrationRequest;
import com.example.auth.bean.UserResponse;
import com.example.auth.entity.AppUser;
import com.example.auth.service.LoginService;
import com.example.auth.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping(path = "registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest registrationRequest, HttpServletResponse response) {
        try {
            AppUser appUser = registrationService.register(registrationRequest);
            return buildUserResponse(appUser);
        } catch (Exception e) {
            return buildErrorResponse(e.getLocalizedMessage());
        }
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            AppUser appUser = loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return buildUserResponse(appUser);
        } catch (Exception e) {
            return buildErrorResponse(e.getLocalizedMessage());
        }
    }

    @GetMapping(path = "current")
    public ResponseEntity<?> current() {
        try {
            AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return buildUserResponse(appUser);
        } catch (NullPointerException e) {
        }
        return buildUserResponse(new AppUser());
    }

    private ResponseEntity<?> buildUserResponse(AppUser appUser) {
        return ResponseEntity.ok(new UserResponse(appUser));
    }

    private ResponseEntity<?> buildErrorResponse(String message) {
        return ResponseEntity.ok(new ErrorResponse(message));
    }

}
