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
    private Long contentId;
    private Long moduleId;
    private String contentType;
    private String contentUrl;
}
