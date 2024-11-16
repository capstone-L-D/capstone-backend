package com.example.Module_Service.feignClient;

import com.example.Module_Service.dto.ContentDto;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "content-service", url = "http://localhost:7075/content")
public interface ContentClient {
    @GetMapping("/module/{moduleId}")
    List<ContentDto> getContentByModuleId(@PathVariable String moduleId);

    @PostMapping("/bulk")
    public ResponseEntity<List<ContentDto>> createMultipleContents(@RequestBody List<ContentDto> contents);
}
