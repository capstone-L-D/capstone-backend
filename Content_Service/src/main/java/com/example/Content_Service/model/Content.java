package com.example.Content_Service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    @Id
    private String contentId;
    private String moduleId;
    private String ContentTitle;
    private String contentType;
    private String contentUrl;
    private  boolean isCompleted;

}
