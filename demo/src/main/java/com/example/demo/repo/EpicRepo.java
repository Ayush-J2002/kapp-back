package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.pojo.Epic;

@Repository
public interface EpicRepo extends JpaRepository<Epic,Integer>{

   
   // Epic findById(int id);

}
