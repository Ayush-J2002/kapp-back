package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



import java.lang.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;
import com.example.demo.pojo.Sprint;
import com.example.demo.repo.EpicRepo;
import com.example.demo.repo.FeatureRepo;
import com.example.demo.repo.SprintRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;

@Service
public class FeatureServiceImpl implements FeatureService {
    @Autowired
    private EpicRepo epicRepository;

    @Autowired
    private FeatureRepo featureRepository;
    @Autowired
    private SprintRepo sprintRepo;

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
    public Feature CreatingFeature(FeatureDto featureDto) {
        Feature feature = new Feature();
        if(featureDto.getSprintId()!=null){
        Sprint sprint=sprintRepo.findById(Integer.parseInt(featureDto.getSprintId())).orElse(null);
        feature.setSprint(sprint);
    }
        Feature savedFeature=feature.getFeature(featureDto);
        return featureRepository.save(savedFeature);

    }

    @Override
    public List<Feature> findAllFeatures() {
        
        return featureRepository.findAll();
    }

    

    @Override
    public List<Epic> createEpicFeature(List<EpicDto> epicDtos, int featureId) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        List<Epic> epics = epicDtos.stream().map(epicDto -> {
            Epic epic = new Epic();
            Epic savedEpic=epic.getEpic(epicDto);
            savedEpic.setFeature(feature);
            return savedEpic;
        }).collect(Collectors.toList());
        // feature.getEpics().addAll(epics);
        // feature.setEpics(epics);
        // Feature epicFeature = featureRepository.save(feature);
        return epicRepository.saveAll(epics);
        
    }
    // @Override
    // public List<Epic> createEpicFeature(List<EpicDto> epicDtos, int featureId) {
    //     Feature feature = featureRepository.findById(featureId)
       
    //       .orElseThrow(() -> new RuntimeException("Feature not found"));
    //       List<Epic> listepic =null;
    //       for(EpicDto epics:epicDtos){
    //         System.out.println(epics.getDescription());

    //         Epic epic=new Epic();
    //         epic.getEpic(epics);
    //         epic.setFeature(feature);
    //         listepic.add(epic);
    //       }
    //       return epicRepository.saveAll(listepic);
    // }




    

  
    @Transactional
    public Feature updateFeature(int featureId, String plannedFor) {
        Feature feature = featureRepository.findById(featureId)
        .orElseThrow(() -> new RuntimeException("Feature not found"));
        feature.setPlannedFor(plannedFor);
        return feature;
        
    }

    @Override
    public Epic createEpic(int featureId, EpicDto epicDto) {
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
                Epic epic = new Epic();
                Epic savedEpic=epic.getEpic(epicDto);
                savedEpic.setFeature(feature);
                return epicRepository.save(savedEpic);
    }
    public Feature updateFeatureBySprint(int featureId, Sprint sprint) {
        Feature newfeature=featureRepository.findById(featureId).orElse(null);
        if(sprint==null){
            newfeature.setSprintIdtoNull(sprint);
            return featureRepository.save(newfeature);
        }
        newfeature.setSprint(sprint);
        return featureRepository.save(newfeature);
     }
  
}
