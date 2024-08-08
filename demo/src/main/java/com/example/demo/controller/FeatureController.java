package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;
import com.example.demo.pojo.Sprint;
import com.example.demo.repo.FeatureRepo;
import com.example.demo.service.FeatureService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tpm")
public class FeatureController {
	@Autowired
	private FeatureRepo featureRepo;
	@Autowired
	private FeatureService featureService;

	
	@GetMapping("/getfeature")
	public ResponseEntity<?> getAllFeatures() {
		List<Feature> listFeature= featureService.findAllFeatures();
		return new ResponseEntity<>(listFeature,HttpStatus.OK);
		// return ResponseEntity.ok(listFeature);
	}

	@PostMapping("/createfeature")
	ResponseEntity<?> createFeature(@Valid @RequestBody FeatureDto featureDto,
			BindingResult result) {
				System.out.println(featureDto.toString());
				System.out.println("Sprint ID"+featureDto.getSprintId());
			
		 Feature feature=featureService.CreatingFeature(featureDto);
		 System.out.println(feature.getId());
		 return ResponseEntity.ok(feature);
		

}

	// @PostMapping("/{featureId}")
	// public ResponseEntity<List<Epic>> addEpicsToFeature(@PathVariable int featureId, @RequestBody List<EpicDto> epicDtos) {
	// 	List<Epic> epics = featureService.createEpicFeature(epicDtos, featureId);
	// 	return new ResponseEntity<>(epics,HttpStatus.CREATED);
	// }



	@PostMapping("/{featureId}")
	public ResponseEntity<?> addEpicsToFeature(@PathVariable int featureId, @RequestBody EpicDto epicDto) {
		Epic epics = featureService.createEpic(featureId,epicDto);
		return new ResponseEntity<>(epics,HttpStatus.CREATED);
	}

	

	@DeleteMapping("/{featureId}/{epicId}")
	public void deletingEpic(@PathVariable int featureId, @PathVariable int epicId) {
		featureService.deleteEpic(featureId, epicId);
	}

	@PutMapping("/{featureId}/epics/{epicId}")
	public Epic updatesEpic(@PathVariable int featureId, @PathVariable int epicId, @RequestBody Epic updatedEpic) {
		return featureService.updateEpic(featureId, epicId, updatedEpic);
	}

	@PutMapping("/{featureId}")
    public Feature updateFeature(@PathVariable int featureId, @RequestBody String body) {
		String plannedFor="pending";
        return featureService.updateFeature(featureId, plannedFor);
    }
	@PutMapping("/updateFeature/{featureId}")
    public ResponseEntity<?> updatingFeature(@PathVariable int featureId,@RequestBody(required = false) Sprint sprint){
    Feature newfeature=this.featureService.updateFeatureBySprint(featureId,sprint);
       return (ResponseEntity<?>) ResponseEntity.ok(newfeature);
     
    }
}


