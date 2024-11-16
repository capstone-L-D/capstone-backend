package com.example.course_module_sr.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modules {
    private String moduleId;
    private String moduleTitle;
    private String moduleDuration;
    private boolean isModuleCompleted;

}
