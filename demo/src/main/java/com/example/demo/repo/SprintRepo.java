package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Sprint;
import java.util.Date;


@Repository
public interface SprintRepo extends JpaRepository<Sprint,Integer>{

   Sprint findTopByOrderByEndDateDesc();
    
}
