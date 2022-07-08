package com.example.artpltesttask02.controller;

import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "users")
    public List<User> getUsersAll() {
        return userService.findAllBy();
    }

    @GetMapping(value = "users/{id}")
    public User getPetsByUser(@PathVariable("id") Long id) {
        return userService.findAllById(id);
    }

    @PostMapping(value = "users")
    public User saveUsers(@RequestBody User user) {
        return userService.save(user);
    }
}
