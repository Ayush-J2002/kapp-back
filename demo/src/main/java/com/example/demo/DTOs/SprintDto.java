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
    private String sprint_Name;
 
    private Date start_Date;

	private Date end_Date;

    public Date getStart_Date() {
        return start_Date;
    }
    
    public void setStart_Date(Date start_Date) {
        this.start_Date = start_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }
   
	public String getSprint_Name() {
        return sprint_Name;
    }

    public void setSprint_Name(String sprint_Name) {
        this.sprint_Name = sprint_Name;
    }
}
