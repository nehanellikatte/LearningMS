package com.example.LearningMS.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LearningMS.Entity.*;
import com.example.LearningMS.Repository.enrollmentRepository;

@Service
public class enrollmentService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(enrollmentService.class);
    
    @Autowired
    public enrollmentRepository enrollmentRepository;
    
    public List<enrollment> getAllEnrollments() {
        LOGGER.info("Fetching all enrollment details");
        return enrollmentRepository.findAll();
    }

    public enrollment enrollStudent(student student, course course, String grade) {
        LOGGER.info("Enrolling student with ID {} to course with ID {} and grade {}", student.getId(), course.getCourseId(), grade);
        enrollment enrollment = new enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(grade);
        enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        LOGGER.info("Enrollment created with ID {}", savedEnrollment.getEnrollId());
        return savedEnrollment;
    }

    public List<enrollment> getEnrollmentsByStudent(student student) {
        LOGGER.info("Fetching enrollments for student with ID {}", student.getId());
        return enrollmentRepository.findByStudent(student);
    }

    public List<enrollment> getEnrollmentsByCourse(course course) {
        LOGGER.info("Fetching enrollments for course with ID {}", course.getCourseId());
        return enrollmentRepository.findByCourse(course);
    }

    public enrollment updateGrade(int enrollmentId, String grade) {
        LOGGER.info("Updating grade for enrollment ID {} to {}", enrollmentId, grade);
        Optional<enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        if (enrollment.isPresent()) {
            enrollment updatedEnrollment = enrollment.get();
            updatedEnrollment.setGrade(grade);
            enrollment savedEnrollment = enrollmentRepository.save(updatedEnrollment);
            LOGGER.info("Grade updated for enrollment ID {}", enrollmentId);
            return savedEnrollment;
        } else {
            LOGGER.error("Enrollment with ID {} not found for grade update", enrollmentId);
            return null;
        }
    }
    
    public boolean deleteEnrollment(int enrollId) {
        LOGGER.info("Attempting to delete enrollment with ID {}", enrollId);
        if (enrollmentRepository.existsById(enrollId)) {
            enrollmentRepository.deleteById(enrollId);
            LOGGER.info("Enrollment with ID {} successfully deleted", enrollId);
            return true;
        } else {
            LOGGER.error("Enrollment with ID {} not found for deletion", enrollId);
            return false;
        }
    }
}
