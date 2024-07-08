package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojo.User;

public interface UserService {
    User createNewRecordService(User user);
	List<User> getAllRecordsService();
	Optional<User> getOneRecordService(int id);
	User findByEmail(String email);
	boolean existsByEmail(String email);
}
