package com.example.demo.service;

import java.util.List;


import com.example.demo.DTOs.SprintDto;

import com.example.demo.pojo.Sprint;

public interface SprintService {
    List<Sprint> findAllSprints();
    Sprint CreatingSprint(SprintDto sprintDto);
    Sprint findsprintsbyid();
    

}
