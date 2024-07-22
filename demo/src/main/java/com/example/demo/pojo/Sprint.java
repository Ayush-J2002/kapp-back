package com.example.demo.pojo;

import java.util.Date;
import java.util.List;

import com.example.demo.DTOs.SprintDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Features;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="sprint")
public class Sprint {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
	@JsonProperty("sprintId")
	private int sprintId;

@OneToMany(mappedBy="sprint",cascade=CascadeType.ALL,orphanRemoval=true)
@JsonManagedReference
	private List<Feature> features;
	public List<Feature> getFeatures() {
    return features;}

public void setFeatures(List<Feature> features) {
    this.features = features;
		for(Feature feature:features){
			feature.setSprint(this);
		}
}
    @Column
	private String sprintName;
    
    @Column
	private Date startDate;

    @Column
	private Date endDate;

    // public Sprint() {}
	
	// public Sprint(int id,String sprintName, Date startDate,Date endDate) {
	// 	super();
	// 	this.id = id;
    //     this.sprintName=sprintName;
	// 	this.start_date =startDate;
	// 	this.end_date = endDate;
		
	// }

  

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

    public Sprint getSprint(SprintDto sprintDto){
		Sprint sprint = new Sprint();
        sprint.setSprintName(sprintDto.getSprintName());
		sprint.setStartDate(sprintDto.getStartDate());
		sprint.setEndDate(sprintDto.getEndDate());
		return sprint;
	} 


}
