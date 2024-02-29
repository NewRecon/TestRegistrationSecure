package com.example.TestRegistration.controllers;

import com.example.TestRegistration.entities.User;
import com.example.TestRegistration.services.JdbcUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    JdbcUserDetailService jdbcUserDetailService;

//    @GetMapping("/")
//    public String getLoginPage(){
//        return "loginPage";
//    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(User user){
        jdbcUserDetailService.registerUser(user);
        return "redirect:/startPage";
    }

    @GetMapping("/startPage")
    public String getStartPage(){
        return "startPage";
    }

}
