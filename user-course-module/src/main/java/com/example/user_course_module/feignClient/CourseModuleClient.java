package com.example.user_course_module.feignClient;

import com.example.user_course_module.dto.Modules;
import com.example.user_course_module.dto.ReceivedResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "course-module-sr", url = "http://localhost:7073/api/course-modules")
public interface CourseModuleClient {
    @PostMapping("/modules")
    public List<ReceivedResponse> getModulesByCourseIds(@RequestBody List<String> CourseModuleIds);
}
