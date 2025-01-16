package com.example.Course_service.model;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document
@Data

public class Course {
    @Id
    private String courseId;
    private String courseTitle;
    private String  courseDescription;
    private String courseDuration;
    private String courseLevel;
    private String courseCategory;
    private Date DeadLine;
    private String imgUrl;

}
