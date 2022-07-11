package com.example.auth.controller;


import com.example.auth.entity.AppUser;
import com.example.auth.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "user")
    public List<AppUser> getUsersAll() {
        return userService.findAll();
    }

    @GetMapping(value = "user/{id}")
    public Optional<AppUser> getById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PutMapping("user/{id}")
    public AppUser updateUser(
            @PathVariable("id") AppUser userFromDb,
            @RequestBody AppUser user
    ) {
        BeanUtils.copyProperties(user, userFromDb, "id");
        return userService.save(userFromDb);
    }

    @DeleteMapping("user/{id}")
    public void removeUser(@PathVariable("id") AppUser user) {
        userService.delete(user);
    }


}
