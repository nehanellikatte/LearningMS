package com.example.LearningMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.LearningMS.Entity.instructor;
import com.example.LearningMS.Service.instructorService;

@RestController
@RequestMapping("/instructors")
public class instructorController {
	
	@Autowired
	private instructorService instructorService;
	
	@PostMapping
    public instructor createInstructor(@RequestBody instructor instructor) {
        return instructorService.createInstructor(instructor);
    }
	
	@PutMapping("/{instructorId}")
    public instructor updateInstructor(@PathVariable int instructorId, @RequestBody instructor instructor) {
        return instructorService.updateInstructor(instructorId, instructor);
    }
	
	@DeleteMapping("/{instructorId}")
    public void deleteInstructor(@PathVariable int instructorId) {
        instructorService.deleteInstructor(instructorId);
        
    }
	
	 @GetMapping("/{instructorId}")
	    public Optional<instructor> getInstructorById(@PathVariable int instructorId) {
	        return instructorService.getInstructorById(instructorId);
	    }

	    @GetMapping
	    public List<instructor> getAllInstructors() {
	        return instructorService.getAllInstructors();
	    }
}
