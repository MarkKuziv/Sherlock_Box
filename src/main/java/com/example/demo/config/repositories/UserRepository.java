package com.example.demo.config.repositories;

import com.example.demo.config.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findByLastName(String lastName);
}
