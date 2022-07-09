package com.example.artpltesttask02.controller;

import com.example.artpltesttask02.DTO.UserLoginDTO;
import com.example.artpltesttask02.entity.Role;
import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.repository.RoleRepository;
import com.example.artpltesttask02.service.CustomUserDetailsService;
import com.example.artpltesttask02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/v1/auth")
public class AuthsController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthsController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value = "check/{name}")
    public ResponseEntity existsByUsername(@PathVariable String name) {
        if(userService.existsByUsername(name)){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User not exists, you can used this username.", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getUsername() == null || userLoginDTO.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you cant used null field");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetailsService userDetailsService = (CustomUserDetailsService) authentication.getPrincipal();

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }


    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserLoginDTO userLoginDTO) {
        if (userService.existsByUsername(userLoginDTO.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(userLoginDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userService.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }


}
