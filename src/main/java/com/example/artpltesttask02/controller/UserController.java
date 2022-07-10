package com.example.artpltesttask02.controller;

import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "user")
    public List<User> getUsersAll() {
        return userService.findAllBy();
    }

    @GetMapping(value = "user/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PutMapping("user/{id}")
    public User updateUser(
            @PathVariable("id") User userFromDb,
            @RequestBody User user
    ) {
        BeanUtils.copyProperties(user, userFromDb, "id");
        return userService.save(userFromDb);
    }

    @DeleteMapping("user/{id}")
    public void removeUser(@PathVariable("id") User user) {
        userService.delete(user);
    }


}
