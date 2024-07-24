package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.DTOs.SprintDto;

import com.example.demo.pojo.Sprint;
import com.example.demo.repo.FeatureRepo;
import com.example.demo.repo.SprintRepo;



@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepo sprintRepo;
   

    @Override
    public List<Sprint> findAllSprints() {
        return sprintRepo.findAll();
    }

    @Override
    public Sprint CreatingSprint(SprintDto sprintDto) {
       
        Sprint sprint = new Sprint();
         Sprint savesp=sprint.getSprint(sprintDto);
        return sprintRepo.save(savesp);
        
    }

    @Override
    public Sprint findsprintsbyid() {
        Date curDate=new Date();
        List<Sprint> list=sprintRepo.findAll();
        for (Sprint sprint : list) {
            if(sprint.getEndDate().after(curDate)){
                return sprint;
            }
        }
        return null;
    }

   
    
}
