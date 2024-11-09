package com.example.LearningMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int insId;
	public int getInsId() {
		return insId;
	}
	public void setInsId(int insId) {
		this.insId = insId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	private String name;
	private String contact;
	
	
	

}
