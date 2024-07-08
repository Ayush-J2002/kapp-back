package com.example.demo.pojo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="features")
public class Feature {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@OneToMany(mappedBy="feature",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Epic> epics;
	@Column
	private String type;
	@Column
	private String filedAgainst;
	@Column
	private String createdBy;
	@Column
	private String description;
	@Column
	private Date createdDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setEpics(List<Epic> epic){
		this.epics=epic;
	}
	
	
}
