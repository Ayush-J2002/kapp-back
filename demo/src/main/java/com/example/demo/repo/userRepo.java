package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.NormalUSer;

@Repository
public interface UserRepo extends JpaRepository<NormalUSer,Integer> {

	NormalUSer findByEmail(String email);
	boolean existsByEmail(String email);


}
