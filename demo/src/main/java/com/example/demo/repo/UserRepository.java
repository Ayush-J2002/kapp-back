package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

