package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;
import com.example.demo.repo.UserRepo;

@Service
public class userServiceImpl implements userService{

	@Autowired
	UserRepo userRepo;
	@Override
    public User createNewRecordService(User user) {
        return userRepo.save(user);
    }
 
    @Override
    public List<User> getAllRecordsService() {
        List<User> userList = (List<User>) userRepo.findAll();
        return userList;
    }
 
	@Override
	public Optional<User> getOneRecordService(int id) {
		Optional<User> b = userRepo.findById(id);
		return b;
	}

	@Override
	public User findByEmail(String email) {
		User user= userRepo.findByEmail(email);
		return user;
	}
	
	@Override
	public boolean existsByEmail(String email) {
	    return userRepo.existsByEmail(email);
	}

   
}