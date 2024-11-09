package com.example.LearningMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearningMS.Entity.course;
import com.example.LearningMS.Entity.enrollment;
import com.example.LearningMS.Entity.student;

public interface enrollmentRepository extends JpaRepository<enrollment, Integer>{
	List<enrollment> findByStudent(student student);
	List<enrollment> findByCourse(course course);
	
}
