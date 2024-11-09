package com.example.LearningMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningMS.Entity.student;
import com.example.LearningMS.Service.studentservice;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/students")
public class studentController {
	
	@Autowired
    private studentservice studentService;

	 @PostMapping
	    public student createStudent(@RequestBody student student) {
	        return studentService.creaStudent(student);
	    }
    
   

    @GetMapping("/{studentId}")
    public Optional<student> getStudentById(@PathVariable int studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }

}
