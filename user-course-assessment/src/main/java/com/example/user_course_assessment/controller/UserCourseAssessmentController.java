package com.example.user_course_assessment.controller;

import com.example.user_course_assessment.model.UserCourseAssessment;
import com.example.user_course_assessment.repo.UserCourseAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping("/user-course-assessments")
public class UserCourseAssessmentController {
    @Autowired
    private UserCourseAssessmentRepository userCourseAssessmentRepository;

    @PostMapping("/start")
    public UserCourseAssessment startAssessment(@RequestParam String userCourseId, @RequestParam String assessmentId) {
        UserCourseAssessment userCourseAssessment = new UserCourseAssessment();
        userCourseAssessment.setUserCourseId(userCourseId);
        userCourseAssessment.setAssessmentId(assessmentId);
        userCourseAssessment.setStatus("In Progress");
        userCourseAssessment.setAttemptDate(LocalDate.now());
        return userCourseAssessmentRepository.save(userCourseAssessment);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> completeAssessment(@PathVariable String id) {
        UserCourseAssessment userCourseAssessment = userCourseAssessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserCourseAssessment not found"));

        // Assuming logic to calculate score and status
         // Example score
        userCourseAssessment.setStatus(userCourseAssessment.getScore() >= 60 ? "Passed" : "Failed");
        userCourseAssessmentRepository.save(userCourseAssessment);

        return ResponseEntity.ok("Assessment completed and result calculated.");
    }
}

