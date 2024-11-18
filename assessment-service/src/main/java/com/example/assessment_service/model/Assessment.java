package com.example.assessment_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Assessment {
    @Id
    private String id;
    private String courseId;
    private String title;
    private Integer totalScore;
    private Integer passScore;
    private Integer timeLimit;
    private String description; // Add this
    private List<String> instructions; // Add this
    private List<Question> questions;


}

