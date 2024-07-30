package com.example.demo.pojo;

import java.util.Date;
import java.util.List;

import com.example.demo.DTOs.EpicDto;
import com.example.demo.DTOs.FeatureDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="features")
public class Feature {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	@JsonProperty("featureId")
	private int featureId;
	@ManyToOne
	@JoinColumn(name="sprint_id")
	@JsonBackReference
	private Sprint sprint;
	private String type;
	private String filedAgainst;
	private String createdBy;
	private String description;
	private Date createdDate;
	private String plannedFor;
	@OneToMany(mappedBy="feature",cascade=CascadeType.ALL,orphanRemoval=true)
	@JsonManagedReference
	private List<Epic> epics;


    @JsonProperty("sprintId")
public Integer getsprintId() {
    return sprint != null ? sprint.getSprintId() : null;
}



	public Sprint getSprint() {
		return sprint;
	}
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public void setPlannedFor(String plannedFor) {
		this.plannedFor = plannedFor;
	}
	public String getPlannedFor() {
		return plannedFor;
	}
	public int getId() {
		return featureId;
	}
	public void setId(int id) {
		this.featureId = id;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<Epic> getEpics(){
		return epics;
	} 
	public void setEpics(List<Epic> epics){
		
		this.epics=epics;
		for(Epic epic:epics){
			epic.setFeature(this);
		}
		
	}
	public void setSprintIdtoNull(Sprint s){
		this.sprint=s;
	}
	public Feature getFeature(FeatureDto featureDto){
		Feature feature=new Feature();
		feature.setType(featureDto.getType());
		feature.setFiledAgainst(featureDto.getFiledAgainst());
		feature.setCreatedBy(featureDto.getCreatedBy());
		feature.setDescription(featureDto.getDescription());
		feature.setPlannedFor(featureDto.getPlannedFor());
		feature.setCreatedDate(new Date());
		return feature;
	}

	
	
	
}
