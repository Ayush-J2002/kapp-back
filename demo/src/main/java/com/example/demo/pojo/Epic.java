package com.example.demo.pojo;

import java.util.Date;

import com.example.demo.DTOs.EpicDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="epics")
public class Epic  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int epicId;

	@ManyToOne
	@JoinColumn(name="feature_id")
	@JsonBackReference
	private Feature feature;
	private String filedAgainst;
	
	private String ownedBy;
	private Date createdAt;
	private Date duedate;
	@Column
	private String description;
	public int getId() {
		return epicId;
	}
	public void setId(int id) {
		this.epicId = id;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public String getFiledAgainst() {
		return filedAgainst;
	}
	public void setFiledAgainst(String filedAgainst) {
		this.filedAgainst = filedAgainst;
	}
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Epic getEpic(EpicDto epicDto){
		Epic epic = new Epic();
		epic.setFiledAgainst(epicDto.getFiledAgainst());
		epic.setOwnedBy(epicDto.getOwnedBy());
		epic.setDuedate(epicDto.getDuedate());
		epic.setDescription(epicDto.getDescription());
		epic.setCreatedAt(new Date());
		epic.setFeature(feature);
		return epic;
	} 
	
	

}
