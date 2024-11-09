package com.example.LearningMS.UnitTesting;

import com.example.LearningMS.Entity.*;

import com.example.LearningMS.Repository.enrollmentRepository;
import com.example.LearningMS.Service.enrollmentService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class enrollmentTest {

    @Mock
    private enrollmentRepository enrollmentRepository;

    @InjectMocks
    private enrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEnrollments() {
        List<enrollment> enrollments = new ArrayList<>();
        enrollments.add(new enrollment());
        when(enrollmentRepository.findAll()).thenReturn(enrollments);

        List<enrollment> result = enrollmentService.getAllEnrollments();

        assertEquals(1, result.size());
        verify(enrollmentRepository, times(1)).findAll();
    }

    @Test
    void testEnrollStudent() {
        student student = new student();
        course course = new course();
        enrollment enrollment = new enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade("A");

        when(enrollmentRepository.save(any(enrollment.class))).thenReturn(enrollment);

        enrollment savedEnrollment = enrollmentService.enrollStudent(student, course, "A");

        assertEquals("A", savedEnrollment.getGrade());
        verify(enrollmentRepository, times(1)).save(any(enrollment.class));
    }

    @Test
    void testUpdateGrade() {
        int enrollmentId = 1;
        String grade = "B";
        enrollment enrollment = new enrollment();
        enrollment.setEnrollId(enrollmentId);
        enrollment.setGrade("A");

        when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.of(enrollment));
        when(enrollmentRepository.save(any(enrollment.class))).thenReturn(enrollment);

        enrollment updatedEnrollment = enrollmentService.updateGrade(enrollmentId, grade);

        assertEquals(grade, updatedEnrollment.getGrade());
        verify(enrollmentRepository, times(1)).findById(enrollmentId);
        verify(enrollmentRepository, times(1)).save(enrollment);
    }
}
