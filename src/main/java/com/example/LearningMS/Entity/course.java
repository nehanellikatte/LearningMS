package com.example.LearningMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	private String name;
	private String descrition;
	private int credits;
	
	@ManyToOne
	private instructor insId;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public instructor getInsId() {
		return insId;
	}

	public void setInsId(instructor insId) {
		this.insId = insId;
	}
	
}
