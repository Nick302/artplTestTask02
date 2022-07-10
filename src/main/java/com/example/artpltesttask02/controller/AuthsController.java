package com.example.artpltesttask02.controller;

import com.example.artpltesttask02.bean.ErrorResponse;
import com.example.artpltesttask02.bean.LoginRequest;
import com.example.artpltesttask02.bean.RegistrationRequest;
import com.example.artpltesttask02.bean.UserResponse;
import com.example.artpltesttask02.configuration.JwtTokenProvider;
import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.service.LoginService;
import com.example.artpltesttask02.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@ResponseBody
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class AuthsController {

    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(path = "registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest registrationRequest, HttpServletResponse response) {
        try {
            User user = registrationService.register(registrationRequest);
            setAuthToken(user, response);
            setRefreshToken(user, response);
            return buildUserResponse(user);
        } catch (Exception e) {

            clearAuthAndRefreshTokens(response);
            return buildErrorResponse(e.getLocalizedMessage());
        }
    }

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            User user = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
            setAuthToken(user, response);
            setRefreshToken(user, response);
            return buildUserResponse(user);
        } catch (Exception e) {

            clearAuthAndRefreshTokens(response);
            return buildErrorResponse(e.getLocalizedMessage());
        }
    }

    @GetMapping(path = "current")
    public ResponseEntity<?> current() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return buildUserResponse(user);
        } catch (NullPointerException e) {

        }
        return buildUserResponse(new User());
    }

    @GetMapping(path = "logout")
    public ResponseEntity<?> logout(HttpServletResponse httpServletResponse) {
        clearAuthAndRefreshTokens(httpServletResponse);
        SecurityContextHolder.clearContext();
        return buildUserResponse(new User());
    }

    private void clearAuthAndRefreshTokens(HttpServletResponse httpServletResponse) {
        Cookie authCookie = new Cookie(jwtTokenProvider.getAuthCookieName(), "-");
        authCookie.setPath(jwtTokenProvider.getCookiePath());

        Cookie refreshCookie = new Cookie(jwtTokenProvider.getRefreshCookieName(), "-");
        refreshCookie.setPath(jwtTokenProvider.getCookiePath());

        httpServletResponse.addCookie(authCookie);
        httpServletResponse.addCookie(refreshCookie);
    }

    private void setAuthToken(User user, HttpServletResponse httpServletResponse) {
        String token = jwtTokenProvider.createAuthToken(user.getUsername(), user.getRole().name());
        Cookie cookie = new Cookie(jwtTokenProvider.getAuthCookieName(), token);
        cookie.setPath(jwtTokenProvider.getCookiePath());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(jwtTokenProvider.getAuthExpirationCookie());
        httpServletResponse.addCookie(cookie);
    }

    private void setRefreshToken(User user, HttpServletResponse httpServletResponse) {
        String token = jwtTokenProvider.createRefreshToken(user.getUsername(), user.getRole().name());
        Cookie cookie = new Cookie(jwtTokenProvider.getRefreshCookieName(), token);
        cookie.setPath(jwtTokenProvider.getCookiePath());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(jwtTokenProvider.getRefreshExpirationCookie());
        httpServletResponse.addCookie(cookie);
    }

    private ResponseEntity<?> buildUserResponse(User user) {
        return ResponseEntity.ok(new UserResponse(user));
    }

    private ResponseEntity<?> buildErrorResponse(String message) {
        return ResponseEntity.ok(new ErrorResponse(message));
    }


}
