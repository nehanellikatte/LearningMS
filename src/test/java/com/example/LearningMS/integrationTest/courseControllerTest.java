package com.example.LearningMS.integrationTest;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.example.LearningMS.Service.courseService;

@SpringBootTest
@AutoConfigureMockMvc
public class courseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private courseService courseService;

    private course newCourse;

    @BeforeEach
    public void setup() {
        newCourse = new course();
        newCourse.setName("Data Dtructure");
        newCourse.setDescrition("Advanced Data Dtructure");
        newCourse.setCredits(3);
    }

    @Test
    public void testCreateCourse() throws Exception {
        mockMvc.perform(post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Data Dtructure\",\"descrition\":\"Advanced Data Dtructure\",\"credits\":3}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Data Dtructure"))
                .andExpect(jsonPath("$.descrition").value("Advanced Data Dtructure"))
                .andExpect(jsonPath("$.credits").value(3));
    }

    @Test
    public void testGetCourseById() throws Exception {
        // Create a course
        course savedCourse = courseService.createCourse(newCourse);

        mockMvc.perform(get("/courses/{courseId}", savedCourse.getCourseId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Data Dtructure"))
                .andExpect(jsonPath("$.descrition").value("Advanced Data Dtructure"))
                .andExpect(jsonPath("$.credits").value(3));
    }

    @Test
    public void testUpdateCourse() throws Exception {
        // Create a course
        course savedCourse = courseService.createCourse(newCourse);

        mockMvc.perform(put("/courses/{courseId}", savedCourse.getCourseId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Advanced Data Dtructure\",\"descrition\":\"Advanced level Data Dtructure\",\"credits\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Advanced Data Dtructure"))
                .andExpect(jsonPath("$.descrition").value("Advanced level Data Dtructure"))
                .andExpect(jsonPath("$.credits").value(4));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        // Create a course
        course savedCourse = courseService.createCourse(newCourse);

        mockMvc.perform(delete("/courses/{courseId}", savedCourse.getCourseId()))
                .andExpect(status().isOk());

    
    }

    @Test
    public void testGetAllCourses() throws Exception {    
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Wireless technology"))
                .andExpect(jsonPath("$[0].descrition").value("Advanced Wireless technology"))
                .andExpect(jsonPath("$[0].credits").value(4))
                .andExpect(jsonPath("$[1].name").value("HTML"))
                .andExpect(jsonPath("$[1].descrition").value("HTML&HTML5"))
                .andExpect(jsonPath("$[1].credits").value(2));
    }
}

