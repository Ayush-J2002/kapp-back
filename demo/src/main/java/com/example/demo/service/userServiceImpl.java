package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.NormalUSer;
import com.example.demo.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	@Override
    public NormalUSer createNewRecordService(NormalUSer user) {
        return userRepo.save(user);
    }
 
    @Override
    public List<NormalUSer> getAllRecordsService() {
        List<NormalUSer> userList = (List<NormalUSer>) userRepo.findAll();
        return userList;
    }
 
	@Override
	public Optional<NormalUSer> getOneRecordService(int id) {
		Optional<NormalUSer> b = userRepo.findById(id);
		return b;
	}

	@Override
	public NormalUSer findByEmail(String email) {
		NormalUSer user= userRepo.findByEmail(email);
		return user;
	}
	
	@Override
	public boolean existsByEmail(String email) {
	    return userRepo.existsByEmail(email);
	}

   
}