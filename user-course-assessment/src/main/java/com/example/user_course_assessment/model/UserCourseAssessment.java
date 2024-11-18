package com.example.user_course_assessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_course_assessments")
public class UserCourseAssessment {
    @Id
    private String id;
    private String userCourseId; // Links to UserCourse
    private String assessmentId; // Links to Assessment
    private Integer score;
    private String status; // e.g., "In Progress", "Passed", "Failed"
    private LocalDate attemptDate;
}

