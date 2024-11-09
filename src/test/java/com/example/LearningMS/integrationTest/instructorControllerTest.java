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

import com.example.LearningMS.Entity.instructor;
import com.example.LearningMS.Service.instructorService;

@SpringBootTest
@AutoConfigureMockMvc
public class instructorControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private instructorService instructorService;
    
    private instructor newInstructor;
    
    @BeforeEach
    public void setup() {
    	newInstructor=new instructor();
    	newInstructor.setName("Radha");
    	newInstructor.setContact("7854125877");
    }
    
    @Test
    public void testCreateinstructor() throws Exception {
    	mockMvc.perform(post("/instructors")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content("{\"name\":\"Radha\",\"contact\":7854125877}"))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.name").value("Radha"))
    	.andExpect(jsonPath("$.contact").value("7854125877"));
    }

    @Test
    public void testGetInstructorById() throws Exception {
        
        instructorService.createInstructor(newInstructor);

        mockMvc.perform(get("/instructors/{id}", newInstructor.getInsId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Radha"))
            	.andExpect(jsonPath("$.contact").value("7854125877"));
    }

    @Test
    public void testUpdateInstructor() throws Exception {
    	instructor savedInstructor = instructorService.createInstructor(newInstructor);
        
        

        mockMvc.perform(put("/instructors/{id}", savedInstructor.getInsId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Anusha H\",\"contact\":7854125877}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Anusha H"))
            	.andExpect(jsonPath("$.contact").value("7854125877"));
    }

    @Test
    public void testDeleteInstructor() throws Exception {
        instructor savedInstructor = instructorService.createInstructor(newInstructor);

        mockMvc.perform(delete("/instructors/{id}", savedInstructor.getInsId()))
                .andExpect(status().isOk());

       
    }

    @Test
    public void testGetAllInstructors() throws Exception {
        

        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Anusha"))
                .andExpect(jsonPath("$[0].contact").value("7854125587"))
                .andExpect(jsonPath("$[1].name").value("Neha N"))
                .andExpect(jsonPath("$[1].contact").value("9965832444"));
    }
}
