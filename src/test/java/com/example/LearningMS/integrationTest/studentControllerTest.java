package com.example.LearningMS.integrationTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.LearningMS.Controller.studentController;
import com.example.LearningMS.Entity.student;
import com.example.LearningMS.Service.studentservice;

@WebMvcTest(studentController.class)
public class studentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private studentservice studentService;
	
	private student newstudent;
	
	@BeforeEach
	public void setup() {
		newstudent=new student();
		newstudent.setName("Neha");
		newstudent.setContact(2345678988L);
		
	}
	
	@Test
	public void testCreateStudent() throws Exception{
		when(studentService.creaStudent(any(student.class))).thenReturn(newstudent);
		mockMvc.perform(post("/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Neha\",\"contact\":2345678988}"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Neha"))
		.andExpect(jsonPath("$.contact").value(2345678988L));
		
		verify(studentService,times(1)).creaStudent(any(student.class));
		
	}
	
	@Test
    public void testGetStudentById() throws Exception {
        when(studentService.getStudentById(1)).thenReturn(Optional.of(newstudent));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Neha"))
                .andExpect(jsonPath("$.contact").value(2345678988L));

        verify(studentService, times(1)).getStudentById(1);
    }
	
	@Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(newstudent));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Neha"))
                .andExpect(jsonPath("$[0].contact").value(2345678988L));

        verify(studentService, times(1)).getAllStudents();
    }

	@Test
    public void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudentById(1);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudentById(1);
    }

}
