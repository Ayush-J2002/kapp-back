package com.example.demo.DTOs;

import java.util.Date;
import java.util.List;

import com.example.demo.pojo.Epic;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FeatureDto {
	private List<EpicDto> epics;
    @NotEmpty(message="The type is required")
	private String type;
	@NotEmpty(message="The feature failed against is required")
    private String filedAgainst;
	@NotEmpty(message="The owner is required")
    private String createdBy;
	@Size(min=1,message="The description should not be less than 10 characters")
    @Size(max=200,message="The description should not be more than 200 characters")
    private String description;
	@NotEmpty(message="Planner for should be mentioned")
	private String plannedFor;
	public String getPlannedFor() {
		return plannedFor;
	}
	public void setPlannedFor(String plannedFor) {
		this.plannedFor = plannedFor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFiledAgainst() {
		return filedAgainst;
	}
	public void setFiledAgainst(String filedAgainst) {
		this.filedAgainst = filedAgainst;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<EpicDto> getEpics(){
		return epics;
	} 
	public void setEpics(List<EpicDto> epic){
		this.epics=epic;

	}

	
	
	
}

