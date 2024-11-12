package com.example.course_module_sr.model;

import com.example.course_module_sr.dto.Course;
import com.example.course_module_sr.dto.Modules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor

public class CourseModuleRelation {
    @Id
    private String courseModuleId;

    private String courseId;
    private String moduleId;
    private String createdAt;


// Getters and setters
}

