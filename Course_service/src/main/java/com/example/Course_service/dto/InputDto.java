package com.example.Course_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {

    private String courseId;
    private String courseTitle;
    private String  courseDescription;
    private String courseDuration;
    private String courseLevel;
    private String courseCategory;
    private String imgUrl;
    private Date DeadLine;
    List<String> selectedModules;
}
