package com.example.user_course_sr.controller;

import com.example.user_course_sr.dto.Course;
import com.example.user_course_sr.dto.Response;
import com.example.user_course_sr.model.UserCourse;
import com.example.user_course_sr.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-courses")
@CrossOrigin
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;



    @PostMapping("/enroll")
    public UserCourse enrollUser(@RequestBody  UserCourse userCourse) {
        return userCourseService.enrollUserInCourse(userCourse.getUserCourseId(),userCourse.getUserId(), userCourse.getCourseId());

    }

    @GetMapping("/{userId}")
    public List<UserCourse> getUserCourses(@PathVariable String userId) {
        return userCourseService.getUserCourses(userId);
    }
    @GetMapping("/courses/{userId}")
    public List<Response> getCoursesForUser(@PathVariable String userId) {
        return userCourseService.getCoursesForUser(userId);
    }
//    @PatchMapping("/{userCourseId}/progress")
//    public void updateProgress(@PathVariable String userCourseId, @RequestParam Double progress) {
//        userCourseService.updateProgress(userCourseId, progress);
//    }

    @GetMapping("/{userId}/completed")
    public List<UserCourse> getCompletedCourses(@PathVariable String userId) {
        return userCourseService.getCompletedCoursesByUser(userId);
    }
}

