package com.example.LearningMS.UnitTesting;



import com.example.LearningMS.Entity.instructor;
import com.example.LearningMS.Repository.instructoryRepository;
import com.example.LearningMS.Service.instructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class instructorTest {

    @Mock
    private instructoryRepository instructoryRepository;

    @InjectMocks
    private instructorService instructorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateInstructor() {
        instructor i = new instructor();
        i.setName("Sahana");
        i.setContact("9019196519");
        
        when(instructoryRepository.save(i)).thenReturn(i);

        instructor createdInstructor = instructorService.createInstructor(i);

        assertEquals("Sahana", createdInstructor.getName());
        assertEquals("9019196519", createdInstructor.getContact());
        verify(instructoryRepository, times(1)).save(i);
    }
    
    
    @Test
    void testGetAllInstructors() {
        List<instructor> instructors = new ArrayList<>();
        instructors.add(new instructor());
        
        when(instructoryRepository.findAll()).thenReturn(instructors);

        List<instructor> result = instructorService.getAllInstructors();

        assertEquals(1, result.size());
        verify(instructoryRepository, times(1)).findAll();
    }
    
    @Test
    void testGetInstructorById() {
        int id = 1;
        instructor i = new instructor();
        i.setName("Anusha");

        when(instructoryRepository.findById(id)).thenReturn(Optional.of(i));

        Optional<instructor> result = instructorService.getInstructorById(id);

        assertTrue(result.isPresent());
        assertEquals("Anusha", result.get().getName());
        verify(instructoryRepository, times(1)).findById(id);
    }
    
    @Test
    void testDeleteInstructor() {
        int instructorId = 1;
        
        doNothing().when(instructoryRepository).deleteById(instructorId);

        instructorService.deleteInstructor(instructorId);

        verify(instructoryRepository, times(1)).deleteById(instructorId);
    }
    
    @Test
    void testUpdateInstructor() {
        int instructorId = 1;
        instructor existingInstructor = new instructor();
        existingInstructor.setName("Radha");
        existingInstructor.setContact("123456789");

        instructor updatedDetails = new instructor();
        updatedDetails.setName("Anusha");
        updatedDetails.setContact("987654321");

        when(instructoryRepository.findById(instructorId)).thenReturn(Optional.of(existingInstructor));
        when(instructoryRepository.save(any(instructor.class))).thenReturn(updatedDetails);

        instructor updatedInstructor = instructorService.updateInstructor(instructorId, updatedDetails);

        assertEquals("Anusha", updatedInstructor.getName());
        assertEquals("987654321", updatedInstructor.getContact());
        verify(instructoryRepository, times(1)).findById(instructorId);
        verify(instructoryRepository, times(1)).save(existingInstructor);
    }
    
    @Test
    void NotFound_testUpdateInstructor() {
        int instructorId = 1;
        instructor updatedDetails = new instructor();
        updatedDetails.setName("Radha");
        updatedDetails.setContact("987654321");

        when(instructoryRepository.findById(instructorId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> instructorService.updateInstructor(instructorId, updatedDetails));

        assertEquals("Instructor Not Found", exception.getMessage());
        verify(instructoryRepository, times(1)).findById(instructorId);
        verify(instructoryRepository, times(0)).save(any(instructor.class));
    }

}

    