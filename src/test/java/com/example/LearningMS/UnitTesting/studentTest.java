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

import com.example.LearningMS.Entity.student;
import com.example.LearningMS.Repository.studentRepository;
import com.example.LearningMS.Service.studentservice;

class studentTest {

    @Mock
    private studentRepository studentRepository;

    @InjectMocks
    private studentservice studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreaStudent() {
        student student = new student();
        student.setName("Neha");

        when(studentRepository.save(student)).thenReturn(student);

        student createdStudent = studentService.creaStudent(student);

        assertEquals("Neha", createdStudent.getName());
        verify(studentRepository).save(student);
    }

    @Test
    void testGetStudentById() {
        student student = new student();
        student.setId(1);
        student.setName("Neha");

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Optional<student> retrievedStudent = studentService.getStudentById(1);

        assertTrue(retrievedStudent.isPresent());
        assertEquals("Neha", retrievedStudent.get().getName());
        verify(studentRepository).findById(1);
    }

    @Test
    void testGetAllStudents() {
        student student1 = new student();
        student1.setName("Student One");

        student student2 = new student();
        student2.setName("Student Two");

        List<student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<student> retrievedStudents = studentService.getAllStudents();

        assertEquals(2, retrievedStudents.size());
        assertEquals("Student One", retrievedStudents.get(0).getName());
        assertEquals("Student Two", retrievedStudents.get(1).getName());
        verify(studentRepository).findAll();
    }

    @Test
    void testDeleteStudentById() {
        int studentId = 1;

        studentService.deleteStudentById(studentId);

        verify(studentRepository).deleteById(studentId);
    }
}
