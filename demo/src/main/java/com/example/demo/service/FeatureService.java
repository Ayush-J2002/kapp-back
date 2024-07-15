package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;

public interface FeatureService {
    List<Feature> findAllFeatures();
    Feature CreatingFeature(FeatureDto featureDto);
    void deleteEpic(int featureId, int epicId);
    Epic updateEpic(int featureId,int epicId,Epic epic);
    Feature createEpicFeature(List<EpicDto> epicDtos,int featureId);
    Feature updateFeature(int featureId, String plannedFor);
}
