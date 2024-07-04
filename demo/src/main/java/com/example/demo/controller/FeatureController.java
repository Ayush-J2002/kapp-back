package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Feature;
import com.example.demo.pojo.FeatureDto;
import com.example.demo.repo.FeatureRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/kap/features")
public class FeatureController {
	@Autowired 
	private FeatureRepo featureRepo;
	
	@GetMapping
	public List<Feature> getAllFeatures(){
		return featureRepo.findAll();
	}
	// @PostMapping("/createfeature")
	// ResponseEntity<Object> createFeature(@Valid @RequestBody FeatureDto featureDto,
	// BindingResult result){
	// 	if(result.hasErrors()){
	// 		var errorList=result.getAllErrors();
	// 		var errorMap=new HashMap<String,String>();
	// 		for(int i=0;i<errorList.size();i++){
	// 			var error=(FieldError)errorList.get(i);
	// 			errorMap.put(error.getField(),error.getDefaultMessage());
	// 		}
	// 		return ResponseEntity.badRequest().body(errorMap);
	// 	}
	// 	Feature feature=new Feature();
	// 	feature.setType(featureDto.getType());
	// 	feature.setFiledAgainst(featureDto.getFiledAgainst());
	// 	feature.setCreatedBy(featureDto.getCreatedBy());
	// 	feature.setDescription(featureDto.getDescription());
	// 	feature.setCreatedDate(new Date());
	// 	featureRepo.save(feature);
	// 	return ResponseEntity.ok(feature);
	// }


	

}
