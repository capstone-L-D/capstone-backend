package com.example.user_course_module.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor

public class UserCourseModule {

    @Id
    private String userCourseModuleId;

    private String userCourseId;       // Foreign key to User
    private String courseModuleId;      // Foreign key to Course

    private Double progress;      // Progress in percentage
    private String completionStatus; // e.g., "In Progress", "Completed"
    private LocalDate startDate;
    private LocalDate completionDate;



    private boolean moduleCompleted;

    // Constructors, Getters, and Setters
}
