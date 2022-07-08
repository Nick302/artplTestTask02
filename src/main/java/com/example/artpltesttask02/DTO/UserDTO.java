package com.example.artpltesttask02.DTO;

import com.example.artpltesttask02.entity.Pets;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String pets;
}
