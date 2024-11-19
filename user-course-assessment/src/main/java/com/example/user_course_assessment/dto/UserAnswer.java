package com.example.user_course_assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserAnswer {

    private String id;
    private String userCourseAssessmentId; // Links to UserCourseAssessment
    List<Options> selectedOptions;
}
