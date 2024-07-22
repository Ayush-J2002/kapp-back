package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.FeatureDto;
import com.example.demo.DTOs.SprintDto;
import com.example.demo.pojo.Feature;
import com.example.demo.pojo.Sprint;
import com.example.demo.repo.SprintRepo;
import com.example.demo.service.SprintService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kap")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @GetMapping("/getSprint")
	public ResponseEntity<?> getAllSprint() {
		List<Sprint> listSprint= sprintService.findAllSprints();
		return new ResponseEntity<>(listSprint,HttpStatus.OK);
	}

	@PostMapping("/createSprint")
	ResponseEntity<?> createSprint(@Valid @RequestBody SprintDto sprintDto,BindingResult result) {
		System.out.println(sprintDto.getEndDate());
		System.out.println(sprintDto.getStartDate());
		System.out.println(sprintDto.getSprintName());

				Sprint sprint=sprintService.CreatingSprint(sprintDto);
				return ResponseEntity.ok(sprint);
	}


    
}
