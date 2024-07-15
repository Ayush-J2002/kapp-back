package com.example.demo.pojo;

import java.util.Date;
import java.util.List;

import com.example.demo.DTOs.SprintDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("SprintId")
	private int id;

@OneToMany(mappedBy="sprint",cascade=CascadeType.ALL,orphanRemoval=true)
@JsonManagedReference
	private List<Feature> features;
	@Column
	private Date start_date;

    @Column
	private Date end_date;

    public Sprint() {}
	
	public Sprint(int id, Date startDate,Date endDate) {
		super();
		this.id = id;
		this.start_date =startDate;
		this.end_date = endDate;
		
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Sprint getSprint(SprintDto sprintDto){
		Sprint sprint = new Sprint();
		sprint.setStart_date(sprintDto.getStart_Date());
		sprint.setEnd_date(sprintDto.getEnd_Date());
		return sprint;
	} 


}
