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
	@JsonProperty("SprintId")
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
	private String sprint_Name;
    
    @Column
	private Date start_Date;

    @Column
	private Date end_Date;

    // public Sprint() {}
	
	// public Sprint(int id,String sprintName, Date startDate,Date endDate) {
	// 	super();
	// 	this.id = id;
    //     this.sprintName=sprintName;
	// 	this.start_date =startDate;
	// 	this.end_date = endDate;
		
	// }

    public int getId() {
        return sprintId;
    }

    public void setId(int id) {
        this.sprintId = id;
    }
    public String getSprintName() {
        return sprint_Name;
    }

    public void setSprintName(String sprintName) {
        this.sprint_Name = sprintName;
    }
    public Date getStart_date() {
        return start_Date;
    }

    public void setStart_date(Date start_Date) {
        this.start_Date = start_Date;
    }

    public Date getEnd_date() {
        return end_Date;
    }

    public void setEnd_date(Date end_date) {
        this.end_Date = end_date;
    }

    public Sprint getSprint(SprintDto sprintDto){
		Sprint sprint = new Sprint();
        sprint.setSprintName(sprintDto.getSprint_Name());
		sprint.setStart_date(sprintDto.getStart_Date());
		sprint.setEnd_date(sprintDto.getEnd_Date());
		return sprint;
	} 


}
