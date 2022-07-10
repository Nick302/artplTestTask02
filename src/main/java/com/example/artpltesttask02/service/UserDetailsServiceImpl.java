package com.example.artpltesttask02.service;

import com.example.artpltesttask02.entity.User;
import com.example.artpltesttask02.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User signUpUser(User user) {

        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("Email is already taken");
        }
        String encodedPassword = encodeString(user.getPassword());

        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user = userRepository.save(user);

        if(!user.isEnabled()){
            throw new IllegalStateException("The user is not enabled yet");
        }
        return user;
    }

    private String encodeString(String password) {
        return passwordEncoder.encode(password);
    }

    public User retrieveFromCache(String username){
        return (User) new CachingUserDetailsService(this).loadUserByUsername(username);
    }

}
