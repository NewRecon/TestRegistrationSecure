package com.example.TestRegistration.services;

import com.example.TestRegistration.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserDetailService {
    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    public void registerUser(User user){
        user.setPassword(encoder().encode(user.getPassword()));
        jdbcUserDetailsManager.createUser(user);
    }
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
