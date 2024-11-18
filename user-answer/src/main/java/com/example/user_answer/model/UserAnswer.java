package com.example.user_answer.model;

import com.example.user_answer.dto.Options;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_answers")
public class UserAnswer {
    @Id
    private String id;
    private String userCourseAssessmentId; // Links to UserCourseAssessment
   List<Options> selectedOptions;
}

