package com.example.user_course_module.controller;

import com.example.user_course_module.dto.Response;
import com.example.user_course_module.model.UserCourseModule;
import com.example.user_course_module.service.UserCourseModuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/user-course-modules")
@CrossOrigin
public class UserCourseModuleController {
    @Autowired
    private UserCourseModuleService userCourseModuleService;
    @PostMapping
    public UserCourseModule saveUserCourseModule(@RequestBody   UserCourseModule userCourseModules) {
        return userCourseModuleService.saveUserCourseModules(userCourseModules);
    }


    @GetMapping("/modules/{userCourseId}")
    public List<Response> getModulesByUserCourseId(@PathVariable String userCourseId) {
        return userCourseModuleService.getModulesByUserCourseId(userCourseId);
    }
}
