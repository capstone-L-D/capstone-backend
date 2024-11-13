package com.example.user_course_module.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivedResponse {
    private String moduleId;
    private String moduleTitle;
    private String moduleDuration;
    private String CourseModuleId;
    private List<ContentDto> contentList;

}
