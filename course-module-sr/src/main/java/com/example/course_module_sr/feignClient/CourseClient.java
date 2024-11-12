package com.example.course_module_sr.feignClient;

import com.example.course_module_sr.dto.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service", url = "http://localhost:7071/api/courses")
public interface CourseClient {
    @GetMapping("/{courseId}")
    Course getCourseById(@PathVariable Long courseId);
}