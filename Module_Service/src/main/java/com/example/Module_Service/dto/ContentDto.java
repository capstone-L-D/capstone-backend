package com.example.Module_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    private Long contentId;
    private String ModuleId;
    private String contentType;
    private String contentUrl;
}
