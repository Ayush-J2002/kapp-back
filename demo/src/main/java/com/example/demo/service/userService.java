package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojo.NormalUSer;

public interface UserService {
    NormalUSer createNewRecordService(NormalUSer user);
	List<NormalUSer> getAllRecordsService();
	Optional<NormalUSer> getOneRecordService(int id);
	NormalUSer findByEmail(String email);
	boolean existsByEmail(String email);
}
