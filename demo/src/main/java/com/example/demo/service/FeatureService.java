package com.example.demo.service;

import java.util.List;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.example.demo.pojo.Epic;
import com.example.demo.pojo.Feature;
import com.example.demo.pojo.Sprint;

public interface FeatureService {
    List<Feature> findAllFeatures();
    Feature CreatingFeature(FeatureDto featureDto);
    void deleteEpic(int featureId, int epicId);
    Epic updateEpic(int featureId,int epicId,Epic epic);
    List<Epic> createEpicFeature(List<EpicDto> epicDtos,int featureId);
    Feature updateFeature(int featureId, String plannedFor);
    Epic createEpic(int featureId,EpicDto epic);
    Feature updateFeatureBySprint(int featureId, Sprint sprint);
}
