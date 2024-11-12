package com.example.Module_Service.dto;

import com.example.Module_Service.model.Modules;
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
