package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.User;



@Repository
public interface UserRepo extends JpaRepository<User,Long> {

	User findByEmail(String email);
	boolean existsByEmail(String email);
	User findByUsername(String username);

}
