package com.example.LearningMS.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int EnrollId;
	
	@ManyToOne
	private student student;
	
	@ManyToOne
	private course course;
	
	private String grade;

	public int getEnrollId() {
		return EnrollId;
	}

	public void setEnrollId(int enrollId) {
		EnrollId = enrollId;
	}

	public student getStudent() {
		return student;
	}

	public void setStudent(student student) {
		this.student = student;
	}

	public course getCourse() {
		return course;
	}

	public void setCourse(course course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
