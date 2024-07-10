package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;
import com.example.demo.repo.EpicRepo;
import com.example.demo.repo.FeatureRepo;

import jakarta.transaction.Transactional;

@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    private EpicRepo epicRepository;

    @Autowired
    private FeatureRepo featureRepository;

    @Transactional
    public void deleteEpic(int featureId, int epicId) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));

        if (epic.getFeature().equals(feature)) {
            epicRepository.delete(epic);
        } else {
            throw new RuntimeException("Epic does not belong to the specified Feature");
        }
    }

    @Transactional
    public Epic updateEpic(int featureId, int epicId, Epic updatedEpic) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));

        if (!(epic.getFeature().getId() == (featureId))) {
            throw new RuntimeException("Epic does not belong to this feature");
        }

        epic.setFiledAgainst(updatedEpic.getFiledAgainst());
        epic.setOwnedBy(updatedEpic.getOwnedBy());
        epic.setDuedate(updatedEpic.getDuedate());
        epic.setDescription(updatedEpic.getDescription());
        epic.setFeature(feature);
        return epicRepository.save(epic);
    }

    @Override
    public Object CreatingFeature(FeatureDto featureDto, BindingResult result) {
        if (result.hasErrors()) {
            var errorList = result.getAllErrors();
            var errorMap = new HashMap<String, String>();
            for (int i = 0; i < errorList.size(); i++) {
                var error = (FieldError) errorList.get(i);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }
        Feature feature = new Feature();
        feature.getFeature(featureDto);
        return featureRepository.save(feature);

    }

    @Override
    public List<Feature> findAllFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public Feature createEpicFeature(List<EpicDto> epicDtos, int featureId) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        List<Epic> epics = epicDtos.stream().map(epicDto -> {
            Epic epic = new Epic();
            epic.getEpic(epicDto);
            return epic;
        }).collect(Collectors.toList());
        feature.getEpics().addAll(epics);
        Feature epicFeature = featureRepository.save(feature);
        return epicFeature;
    }

}
