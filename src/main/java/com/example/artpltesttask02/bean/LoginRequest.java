package com.example.artpltesttask02.bean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;



@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginRequest {
    private final String username;
    private final String password;
}