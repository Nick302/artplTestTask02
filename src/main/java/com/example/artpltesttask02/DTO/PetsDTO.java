package com.example.artpltesttask02.DTO;

import com.example.artpltesttask02.entity.Pets;
import com.example.artpltesttask02.entity.Sex;
import com.example.artpltesttask02.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetsDTO {
    private Long id;
    private String nickName;
    private Date birthDay;
    private String sex;
    private UserDTO userDTO;

    public Pets toPets(){
        Pets pets = new Pets();
        pets.setId(id);
        pets.setBirthDay(birthDay);
        pets.setNickName(nickName);
        pets.setSex(Sex.valueOf(sex));
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setId(userDTO.getId());
        pets.setUsers(user);
        return pets;
    }

}
