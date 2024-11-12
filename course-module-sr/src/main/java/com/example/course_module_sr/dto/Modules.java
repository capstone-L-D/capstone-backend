package com.example.course_module_sr.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modules {
    private String moduleId;
    private String moduleTitle;
    private String moduleDuration;
}
