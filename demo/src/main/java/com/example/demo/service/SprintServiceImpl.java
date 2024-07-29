package com.example.demo.service;

import java.util.Calendar;
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

import jakarta.validation.Valid;



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
       validateSpringDate(sprintDto);
        Sprint sprint = new Sprint();
        sprint.setStartDate(sprintDto.getStartDate());
        sprint.setEndDate(sprintDto.getEndDate());
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

    @Override
    public void validateSpringDate(SprintDto sprintDto) {
        Date sd=sprintDto.getStartDate();
        Date ed=sprintDto.getEndDate();

        Sprint previouSprint=sprintRepo.findTopByOrderByEndDateDesc();
       
        Date preEDate=previouSprint!=null?previouSprint.getEndDate():null;
        if(preEDate!=null && sd!=null&&!isvalid(preEDate,sd)){
            throw new IllegalArgumentException("start date should be 2 days after the previous end date");
        }
    }

    public  boolean isvalid(Date prDate,Date sDate){

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(prDate);
        calendar.add(Calendar.DATE, 2);
        Date minSDate=calendar.getTime();

        return !sDate.before(minSDate);
    }
   @Override
    public Sprint getSprintById(@Valid Integer id) {
        System.out.println(id);
        Sprint sprint = sprintRepo.findById(id).orElse(null);
        if (id == null) {
            return null;
        }
        System.out.println(sprint);
        return sprint;
    }
    
}
