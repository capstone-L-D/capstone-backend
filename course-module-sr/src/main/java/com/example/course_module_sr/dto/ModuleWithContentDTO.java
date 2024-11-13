package com.example.course_module_sr.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleWithContentDTO {
    private Modules module;
    private List<ContentDto> contentList;
}
