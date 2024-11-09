package com.example.LearningMS.integrationTest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.LearningMS.Entity.course;
import com.example.LearningMS.Entity.enrollment;
import com.example.LearningMS.Entity.student;
import com.example.LearningMS.Service.courseService;
import com.example.LearningMS.Service.enrollmentService;
import com.example.LearningMS.Service.studentservice;

@SpringBootTest
@AutoConfigureMockMvc
public class enrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private enrollmentService enrollmentService;

    @Autowired
    private studentservice studentService;

    @Autowired
    private courseService courseService;

    private student newStudent;
    private course newCourse;
    private enrollment newEnrollment;

    @BeforeEach
    public void setup() {
        newStudent = new student();
        newStudent.setName("Shivu");
        newStudent.setContact(1234567890L);
        newStudent = studentService.creaStudent(newStudent);

        newCourse = new course();
        newCourse.setName("Algorithms");
        newCourse.setDescrition("Introduction to Algorithms");
        newCourse.setCredits(4);
        newCourse = courseService.createCourse(newCourse);

        newEnrollment = new enrollment();
        newEnrollment.setStudent(newStudent);
        newEnrollment.setCourse(newCourse);
        newEnrollment.setGrade("A");
        newEnrollment = enrollmentService.enrollStudent(newStudent, newCourse, "A");
    }

    @Test
    public void testEnrollStudent() throws Exception {
        mockMvc.perform(post("/enrollments/enroll")
                .param("studentId", String.valueOf(newStudent.getId()))
                .param("courseId", String.valueOf(newCourse.getCourseId()))
                .param("grade", "B")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student.name").value("Shivu"))
                .andExpect(jsonPath("$.course.name").value("Algorithms"))
                .andExpect(jsonPath("$.grade").value("B"));
    }

    @Test
    public void testGetEnrollmentsByStudent() throws Exception {
        mockMvc.perform(get("/enrollments/student/{studentId}", newStudent.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].student.name").value("Shivu"))
                .andExpect(jsonPath("$[0].course.name").value("Algorithms"))
                .andExpect(jsonPath("$[0].grade").value("A"));
    }

    @Test
    public void testGetEnrollmentsByCourse() throws Exception {
        mockMvc.perform(get("/enrollments/course/{courseId}", newCourse.getCourseId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].student.name").value("Shivu"))
                .andExpect(jsonPath("$[0].course.name").value("Algorithms"))
                .andExpect(jsonPath("$[0].grade").value("A"));
    }

    @Test
    public void testUpdateGrade() throws Exception {
        mockMvc.perform(post("/enrollments/{enrollmentId}/grade", newEnrollment.getEnrollId())
                .param("grade", "S")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade").value("S"));
    }

    @Test
    public void testDeleteEnrollment() throws Exception {
        mockMvc.perform(delete("/enrollments/{enrollId}", newEnrollment.getEnrollId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Enrollment with ID " + newEnrollment.getEnrollId() + " has been deleted."));

        
    }

    @Test
    public void testGetAllEnrollments() throws Exception {
        mockMvc.perform(get("/enrollments/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].student.name").value("Neha"))
                .andExpect(jsonPath("$[0].course.name").value("Wireless technology"))
                .andExpect(jsonPath("$[0].grade").value("A"));
    }
}

