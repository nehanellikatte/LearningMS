package com.example.LearningMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.LearningMS.Entity.course;
import com.example.LearningMS.Service.courseService;

@RestController
@RequestMapping("/courses")
public class courseController {
	
	@Autowired
	private courseService courseService;
	
	@PostMapping
	public course createCourse(@RequestBody course c) {
		return courseService.createCourse(c);
	}
	
	 @PutMapping("/{courseId}")
	    public course updateCourse(@PathVariable int courseId, @RequestBody course course) {
	        return courseService.updateCourse(courseId, course);
	    }
	 
	 @DeleteMapping("/{courseId}")
	    public void deleteCourse(@PathVariable int courseId) {
	        courseService.deleteCourse(courseId);
	        
	    }
	 
	 @GetMapping("/{courseId}")
	    public Optional<course> getCourseById(@PathVariable int courseId) {
	        return courseService.getCourseById(courseId);
	    }
	    
	    @GetMapping
	    public List<course> getAllCourses() {
	        return courseService.getAllCourses();
	    }
	}
	


