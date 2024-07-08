package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Epic;
import com.example.demo.pojo.EpicDto;
import com.example.demo.pojo.Feature;
import com.example.demo.pojo.FeatureDto;
import com.example.demo.repo.FeatureRepo;
import com.example.demo.service.FeatureService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/kap")
public class FeatureController {
	@Autowired 
	private FeatureRepo featureRepo;
	@Autowired
	private FeatureService featureService;
	
	@GetMapping("/getfeature")
	public List<Feature> getAllFeatures(){
		return featureRepo.findAll();
	}
	@PostMapping("/createfeature")
	ResponseEntity<Object> createFeature(@Valid @RequestBody FeatureDto featureDto,
	BindingResult result){
		if(result.hasErrors()){
			var errorList=result.getAllErrors();
			var errorMap=new HashMap<String,String>();
			for(int i=0;i<errorList.size();i++){
				var error=(FieldError)errorList.get(i);
				errorMap.put(error.getField(),error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errorMap);
		}
		Feature feature=new Feature();
		feature.setType(featureDto.getType());
		feature.setFiledAgainst(featureDto.getFiledAgainst());
		feature.setCreatedBy(featureDto.getCreatedBy());
		feature.setDescription(featureDto.getDescription());
		feature.setCreatedDate(new Date());
		featureRepo.save(feature);
		return ResponseEntity.ok(feature);
	}

	@PostMapping("/{featureId}")
		public ResponseEntity<Feature> addEpicsToFeature(@PathVariable int featureId,@RequestBody List<EpicDto> epicDtos){
			Feature feature=featureRepo.findById(featureId).orElseThrow(()->new RuntimeException("Feature not found"));
			List<Epic> epics=epicDtos.stream().map(epicDto->{
				Epic epic=new Epic();
				epic.setFiledAgainst(epicDto.getFiledAgainst());
				epic.setOwnedBy(epicDto.getOwnedBy());
				epic.setDuedate(epicDto.getDuedate());
				epic.setDescription(epicDto.getDescription());
				epic.setCreatedAt(new Date());
				epic.setFeature(feature);
				return epic;
			}).collect(Collectors.toList());
			feature.getEpics().addAll(epics);
			Feature epicFeature=featureRepo.save(feature);
			return ResponseEntity.ok(epicFeature);
		}

		@PostMapping("/{featureId}/{epicId}")
		public void deletingEpic(@PathVariable int featureId,@PathVariable int epicId ){
			featureService.deleteEpic(featureId,epicId);
		}

		// @PostMapping("/updateEpic/{featureId}/{epicId}")
		// public RequestBody<Epic> updateEpic()


	

}
