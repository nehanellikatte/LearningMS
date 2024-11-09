package com.example.LearningMS.Controller;



import com.example.LearningMS.Entity.enrollment;
import com.example.LearningMS.Service.enrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class reportController {

    @Autowired
    private enrollmentService enrollmentService;

    // End point for Course Performance (Average grades per course)
    @GetMapping("/course-performance")
    public Map<String, Double> getCoursePerformance() {
        // Fetch all enrollments
        List<enrollment> enrollments = enrollmentService.getAllEnrollments();
        
        // Group by course and calculate the average grade
        return enrollments.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCourse().getName(),
                        Collectors.averagingDouble(e -> getGradeNumeric(e.getGrade()))
                ));
    }

    // End point for Overall Performance (GPA or grade average across all subjects)
    @GetMapping("/overall-performance")
    public Map<String, Double> getOverallPerformance() {
        // Fetch all enrollments
        List<enrollment> enrollments = enrollmentService.getAllEnrollments();
        
        // Group by student and calculate the average grade
        return enrollments.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getStudent().getName(),
                        Collectors.averagingDouble(e -> getGradeNumeric(e.getGrade()))
                ));
    }

    // Helper method to convert grade to numeric value
    private double getGradeNumeric(String grade) {
        switch (grade) {
        	case "S": return 5.0;
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default: return 0.0;
        }
    }
}

