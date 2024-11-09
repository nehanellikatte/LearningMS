package com.example.LearningMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningMS.Entity.course;
import com.example.LearningMS.Entity.enrollment;
import com.example.LearningMS.Entity.student;
import com.example.LearningMS.Service.courseService;
import com.example.LearningMS.Service.enrollmentService;
import com.example.LearningMS.Service.studentservice;

@RestController
@RequestMapping("/enrollments")
public class enrollmentController {
	
	@Autowired
	private enrollmentService enrollmentService;
	
	@Autowired
	private studentservice  studentservice;
	
	@Autowired
	private courseService courseService;
	
	@GetMapping("/")
	public List<enrollment> getAllEnrollments() {
	    return enrollmentService.getAllEnrollments();
	}

	
	 @PostMapping("/enroll")
	    public enrollment enrollStudent(
	            @RequestParam
	            int studentId,
	            @RequestParam 
	            int courseId,
	            @RequestParam(required = false)
	            String grade) {
	        
	        // Fetching Student and Course objects
	        student student = studentservice.getStudentById(studentId).orElse(null);
	        course course = courseService.getCourseById(courseId).orElse(null);

	        if (student == null || course == null) {
	            throw new IllegalArgumentException("Invalid student or course ID");
	        }
	        	        return enrollmentService.enrollStudent(student, course, grade);
	    }
	 
	// End point to get all enrollments for a particular student
	    @GetMapping("/student/{studentId}")
	    public List<enrollment> getEnrollmentsByStudent(@PathVariable int studentId) {
	        student student = studentservice.getStudentById(studentId).orElse(null);
	        if (student == null) {
	            throw new IllegalArgumentException("Student not found with ID: " + studentId);
	        }

	        return enrollmentService.getEnrollmentsByStudent(student);
	    }
	    
	    
	 // End point to get all enrollments for a particular course
	    @GetMapping("/course/{courseId}")
	    public List<enrollment> getEnrollmentsByCourse(@PathVariable int courseId) {
	        course course = courseService.getCourseById(courseId).orElse(null);

	        if (course == null) {
	            throw new IllegalArgumentException("Course not found with ID: " + courseId);
	        }

	        return enrollmentService.getEnrollmentsByCourse(course);
	    }
	    
	 // End point to update the grade for a specific enrollment
	    @PostMapping("/{enrollmentId}/grade")
	    public enrollment updateGrade(
	            @PathVariable int enrollmentId,
	            @RequestParam String grade) {
	        
	        enrollment updatedEnrollment = enrollmentService.updateGrade(enrollmentId, grade);

	        if (updatedEnrollment == null) {
	            throw new IllegalArgumentException("Enrollment not found with ID: " + enrollmentId);
	        }

	        return updatedEnrollment;
	    }
	    
	    
	    @DeleteMapping("/{enrollId}")
	    public String deleteEnrollment(@PathVariable int enrollId) {
	        boolean isDeleted = enrollmentService.deleteEnrollment(enrollId);
	        if(isDeleted) {
	            return "Enrollment with ID " + enrollId + " has been deleted.";
	        } else {
	            return "Enrollment not found with ID: " + enrollId;
	        }
	    }
}
