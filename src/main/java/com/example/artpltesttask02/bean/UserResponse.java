package com.example.artpltesttask02.bean;

import com.example.artpltesttask02.entity.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResponse extends BaseResponse {
    private String username;
    private boolean enabled;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.enabled = user.isEnabled();
    }
}