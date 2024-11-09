package com.example.LearningMS.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearningMS.Entity.instructor;
import com.example.LearningMS.Repository.instructoryRepository;

@Service
public class instructorService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(instructorService.class);
	
	@Autowired
	private instructoryRepository instructoryRepository;
	
	public instructor createInstructor(instructor i) {
		LOGGER.info("Instructor created with the name {} successfully.",i.getName());
		return instructoryRepository.save(i);	
		}
	
	public List<instructor> getAllInstructors(){
		LOGGER.info("All intructor details retrived successfully.");
		return instructoryRepository.findAll();
		}
	
	public Optional<instructor> getInstructorById (int id){
		LOGGER.info("Instructor details are fetching with id{} successfully",id);
		return instructoryRepository.findById(id);	}
	
	public void deleteInstructor(int instructorId) {
		LOGGER.info("Instructor details are deleted successfully.");
        instructoryRepository.deleteById(instructorId);
    }
	
	public instructor updateInstructor(int instructorId, instructor instructorDetails) {
		LOGGER.info("instructor details are updated successfullyy.");
        Optional<instructor> instructor = instructoryRepository.findById(instructorId);
        if (instructor.isPresent()) {
            instructor updatedInstructor = instructor.get();
            updatedInstructor.setName(instructorDetails.getName());
            updatedInstructor.setContact(instructorDetails.getContact());
            return instructoryRepository.save(updatedInstructor);
        } else {
        	LOGGER.error("Instructor with ID {} not found",instructorId);
        	throw new RuntimeException("Instructor Not Found");
        }
    }
	
}
