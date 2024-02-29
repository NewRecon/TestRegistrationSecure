package com.example.TestRegistration.repositories;

import com.example.TestRegistration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findMyUserByUsername(String username);
}
