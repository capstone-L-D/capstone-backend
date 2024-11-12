package com.example.LMS_UserAuthentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userdb")
public class User {
    @Id
    private long userId;
    private String userName;
    private String userEmail;
    private String password;
}

