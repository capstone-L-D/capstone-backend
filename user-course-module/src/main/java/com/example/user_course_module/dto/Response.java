package com.example.user_course_module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String moduleId;
    private String moduleTitle;
    private String moduleDuration;
    private String userCourseModuleId;
    private List<ContentDto> contentList;

    private String userCourseId;       // Foreign key to User
    private String courseModuleId;      // Foreign key to Course

    private Double progress;      // Progress in percentage
    private String completionStatus; // e.g., "In Progress", "Completed"
    private LocalDate startDate;
    private LocalDate completionDate;
}
