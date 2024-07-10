package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;
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
	public ResponseEntity<?> getAllFeatures() {
		return (ResponseEntity<?>) featureService.findAllFeatures();
	}

	@PostMapping("/createfeature")
	ResponseEntity<?> createFeature(@Valid @RequestBody FeatureDto featureDto,
			BindingResult result) {
		return (ResponseEntity<?>) featureService.CreatingFeature(featureDto,result);
	}

	@PostMapping("/{featureId}")
	public ResponseEntity<Feature> addEpicsToFeature(@PathVariable int featureId, @RequestBody List<EpicDto> epicDtos) {
		Feature feature = featureService.createEpicFeature(epicDtos, featureId);
		return ResponseEntity.ok(feature);
	}

	@DeleteMapping("/{featureId}/{epicId}")
	public void deletingEpic(@PathVariable int featureId, @PathVariable int epicId) {
		featureService.deleteEpic(featureId, epicId);
	}

	@PutMapping("/{featureId}/epics/{epicId}")
	public Epic updatesEpic(@PathVariable int featureId, @PathVariable int epicId, @RequestBody Epic updatedEpic) {
		return featureService.updateEpic(featureId, epicId, updatedEpic);
	}

}
