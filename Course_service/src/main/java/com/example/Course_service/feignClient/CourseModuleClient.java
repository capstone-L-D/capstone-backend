package com.example.Course_service.feignClient;

import com.example.Course_service.dto.CourseModuleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "course-module-sr", url = "http://localhost:7073/api/course-modules")
public interface CourseModuleClient {

    @PostMapping("/bulk")
    public ResponseEntity<List<CourseModuleDto>> createAssociations(@RequestBody List<CourseModuleDto> relations);
}
