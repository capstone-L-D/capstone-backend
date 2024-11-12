package com.example.Module_Service.feignClient;

import com.example.Module_Service.dto.ContentDto;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "content-service", url = "http://localhost:7075")
public interface ContentClient {
    @GetMapping("/content/module/{moduleId}")
    List<ContentDto> getContentByModuleId(@PathVariable String moduleId);
}
