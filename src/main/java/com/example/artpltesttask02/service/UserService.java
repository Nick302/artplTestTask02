package com.example.artpltesttask02.service;

import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  List<User> findAllBy(){
      return  userRepository.findAllBy();
    }

    public User findAllById(Long id){
        return userRepository.findAllById(id);
    }

   public void delete(User entity){
        userRepository.delete(entity);
    }

  public <S extends User> S save(S entity){
       return userRepository.save(entity);
    }

}
