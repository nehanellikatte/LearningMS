package com.example.LearningMS.UnitTesting;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.LearningMS.Service.courseService;

import com.example.LearningMS.Entity.course;
import com.example.LearningMS.Repository.courseRepository;

class courseTest {

    @Mock
    private courseRepository courseRepository;

    @InjectMocks
    private courseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse() {
        course c = new course();
        c.setName("Java");

        when(courseRepository.save(c)).thenReturn(c);

        course createdCourse = courseService.createCourse(c);

        assertEquals("Java", createdCourse.getName());
        verify(courseRepository).save(c);
    }

    @Test
    void testGetAllCourses() {
        course course1 = new course();
        course1.setName("Python");

        course course2 = new course();
        course2.setName("Java");

        List<course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<course> retrievedCourses = courseService.getAllCourses();

        assertEquals(2, retrievedCourses.size());
        assertEquals("Python", retrievedCourses.get(0).getName());
        assertEquals("Java", retrievedCourses.get(1).getName());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetCourseById() {
        course course = new course();
        
        course.setName("C++");

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Optional<course> retrievedCourse = courseService.getCourseById(1);

        assertTrue(retrievedCourse.isPresent());
        assertEquals("C++", retrievedCourse.get().getName());
        verify(courseRepository).findById(1);
    }

    @Test
    void testDeleteCourse() {
        int courseId = 1;
        course courseDelete = new course();
       
        courseDelete.setName("Physics");

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(courseDelete));

        courseService.deleteCourse(courseId);

        verify(courseRepository).findById(courseId);
        verify(courseRepository).deleteById(courseId);
    }

    @Test
    void testUpdateCourse() {
        int courseId = 1;
        course existingCourse = new course();
        
        existingCourse.setName("Java");
        existingCourse.setDescrition("Advanced Java");
        existingCourse.setCredits(3);

        course updatedDetails = new course();
        updatedDetails.setName("Spring boot");
        updatedDetails.setDescrition("Spring boot application");
        updatedDetails.setCredits(4);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(any(course.class))).thenAnswer(invocation -> invocation.getArgument(0));

        course updatedCourse = courseService.updateCourse(courseId, updatedDetails);

        assertEquals("Spring boot", updatedCourse.getName());
        assertEquals("Spring boot application", updatedCourse.getDescrition());
        assertEquals(4, updatedCourse.getCredits());
        verify(courseRepository).findById(courseId);
        verify(courseRepository).save(existingCourse);
    }
}
