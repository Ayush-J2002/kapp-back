package com.example.demo.DTOs;

import java.util.Date;
import java.util.List;

public class SprintDto {
    private List<FeatureDto> feature;
    public List<FeatureDto> getFeature() {
        return feature;
    }
    public void setFeature(List<FeatureDto> feature) {
        this.feature = feature;
    }
    private String sprintName;
 
    private Date startDate;

	private Date endDate;
    public String getSprintName() {
        return sprintName;
    }
    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

  
}
