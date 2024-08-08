package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Sprint;



@Repository
public interface SprintRepo extends JpaRepository<Sprint,Integer>{

   Sprint findTopByOrderByEndDateDesc();
    
}
