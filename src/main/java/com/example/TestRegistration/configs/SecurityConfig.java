package com.example.TestRegistration.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(req->{
            req.requestMatchers("/registration").permitAll().anyRequest().authenticated();
        });
        http.formLogin(log->{
            log.loginPage("/login").permitAll();
        });
        http.httpBasic(Customizer.withDefaults());
        http.csrf(cust->{
            cust.disable();
        });
        return http.build();
    }

    @Bean
    JdbcUserDetailsManager setUserService(){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(setUserService());
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }
}
