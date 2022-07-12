package com.example.auth.controller;

import com.example.auth.service.LoginService;
import com.example.auth.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthControllerTest {

    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final AuthController controller;

    @Autowired
    public AuthControllerTest(RegistrationService registrationService, LoginService loginService, AuthController controller) {
        this.registrationService = registrationService;
        this.loginService = loginService;
        this.controller = controller;
    }

    @Test
    void findByEmailTest() {
        String result = String.valueOf(controller.findByEmail("nchernov@gmail.com"));
        assertEquals(result,"nchernov@gmail.com");
    }
}