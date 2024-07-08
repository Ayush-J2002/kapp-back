package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;
import com.example.demo.repo.EpicRepo;
import com.example.demo.repo.FeatureRepo;

import jakarta.transaction.Transactional;
@Service
public class FeatureServiceImpl implements FeatureService{
    @Autowired
    private EpicRepo epicRepository;
 
    @Autowired
    private FeatureRepo featureRepository;
 
    @Transactional
    public void deleteEpic(int featureId, int epicId) {
        Feature feature = featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));
        Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new RuntimeException("Epic not found"));
 
        if (epic.getFeature().equals(feature)) {
            epicRepository.delete(epic);
        } else {
            throw new RuntimeException("Epic does not belong to the specified Feature");
        }
    }

}
