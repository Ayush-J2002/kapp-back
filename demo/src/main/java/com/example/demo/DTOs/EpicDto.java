package com.example.demo.DTOs;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EpicDto{
	//private int id;
    @NotEmpty(message="it is mandatory to mention against whom the epic is filed")
    private String filedAgainst;
    @NotEmpty(message="it is necessary to mention the ownedby")
	private String ownedBy;
    @NotEmpty(message="It is required to mention the duedate of the epic")
	private Date duedate;
    @Size(min=10,message="The description should not be less than 10 characters")
    @Size(max=200,message="The description should not be more than 200 characters")
    private String description;
  
	
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
}