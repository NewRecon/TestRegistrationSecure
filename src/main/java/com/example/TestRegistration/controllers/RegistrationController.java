package com.example.TestRegistration.controllers;

import com.example.TestRegistration.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @GetMapping
    public String getIndex(){
        return "startPage";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "loginPage";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user){
        user.setPassword(encoder().encode(user.getPassword()));
        jdbcUserDetailsManager.createUser(user);
        return "redirect:/login";
    }

    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
