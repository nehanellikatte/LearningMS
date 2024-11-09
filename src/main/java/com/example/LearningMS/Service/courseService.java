package com.example.LearningMS.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearningMS.Entity.course;
import com.example.LearningMS.Repository.courseRepository;

@Service
public class courseService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(courseService.class);
	
	@Autowired
	private courseRepository courseRepository;
	
	public course createCourse(course c) {
		LOGGER.info("Creating a new course with name:{}",c.getName());
		return courseRepository.save(c);
	}
	
	public List<course> getAllCourses(){
		LOGGER.info("Retriving all the course successfully.}");
		return courseRepository.findAll();
	}
	
	public Optional<course> getCourseById(int id) {
		LOGGER.info("retiving course details with iD:",id);
		return courseRepository.findById(id);
		}
	
	public void deleteCourse(int id) {
	    LOGGER.info("Attempting to delete course with ID: {}", id);
	    
	    // Check if the course exists
	    course courseToDelete = courseRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Course with ID " + id + " not found"));
	    
	    if (courseToDelete.getInsId() != null) {
	        courseToDelete.setInsId(null); 
	        courseRepository.save(courseToDelete); // Update course in the database
	    }
	    // Delete the course itself
	    courseRepository.deleteById(id);
	    LOGGER.info("Course with ID {} successfully deleted", id);
	}

	public course updateCourse(int courseId, course courseDetails) {
		LOGGER.info("Updating the course with ID:{}",courseId);
        Optional<course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            course updatedCourse = course.get();
            updatedCourse.setName(courseDetails.getName());
            updatedCourse.setDescrition(courseDetails.getDescrition());
            updatedCourse.setCredits(courseDetails.getCredits());
            return courseRepository.save(updatedCourse);
        } else {
        	LOGGER.error("Course with ID {} not found",courseId);
        	throw new RuntimeException("Course Not Found");
        }
    }

}
