package com.example.LearningMS.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearningMS.Entity.student;
import com.example.LearningMS.Repository.studentRepository;

@Service
public class studentservice {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(studentservice.class);
	
	@Autowired
	private studentRepository studentRepository;
	
	public student creaStudent(student student) {
		LOGGER.info("Student record created successfully with name {}",student.getName());
		return studentRepository.save(student);
	}
	
	public Optional<student> getStudentById(int id){
		LOGGER.info("Student record created successfully with ID {}",id);
		return studentRepository.findById(id);
	}
	
	public List<student> getAllStudents(){
		LOGGER.info("All the Students records retrived.");
		return studentRepository.findAll();
	}
	
	 public void deleteStudentById(int id) {
		 LOGGER.info("Student record deleted successfully with id {}",id);
	        studentRepository.deleteById(id);
	    }

}
