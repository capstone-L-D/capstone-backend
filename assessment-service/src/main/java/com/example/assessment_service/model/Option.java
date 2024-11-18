package com.example.assessment_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Option {

    private String optionId;
    private String text;
    private Boolean isCorrect;
}
