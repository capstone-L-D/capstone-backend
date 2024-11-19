package com.example.user_course_assessment.controller;

import com.example.user_course_assessment.dto.Options;
import com.example.user_course_assessment.dto.UserAnswer;
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
@CrossOrigin
public class UserCourseAssessmentController {
    @Autowired
    private UserCourseAssessmentRepository userCourseAssessmentRepository;

    @PostMapping("/start")
    public UserCourseAssessment startAssessment(@RequestParam String userCourseId, @RequestParam String assessmentId) {
        UserCourseAssessment u= userCourseAssessmentRepository.findByUserCourseId(userCourseId).orElse(null);
        if(u==null){
        UserCourseAssessment userCourseAssessment = new UserCourseAssessment();
        userCourseAssessment.setUserCourseId(userCourseId);
        userCourseAssessment.setAssessmentId(assessmentId);
        userCourseAssessment.setStatus("In Progress");
        userCourseAssessment.setAttemptDate(LocalDate.now());

        return userCourseAssessmentRepository.save(userCourseAssessment);}
        else{
            return u;
        }
    }

    @PostMapping("/complete")
    public ResponseEntity<UserCourseAssessment> completeAssessment(@RequestBody UserAnswer userAnswer) {
        UserCourseAssessment userCourseAssessment = userCourseAssessmentRepository.findById(userAnswer.getUserCourseAssessmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserCourseAssessment not found"));

        long totalScore = userAnswer.getSelectedOptions().stream().filter(Options::getIsCorrect).count();
       userCourseAssessment.setScore((int) totalScore);
        userCourseAssessment.setStatus(totalScore >= 6 ? "Passed" : "Failed");

        ;

        return ResponseEntity.ok(userCourseAssessmentRepository.save(userCourseAssessment));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserCourseAssessment> getUserCourseAssessment(@PathVariable String id) {
        UserCourseAssessment userCourseAssessment = userCourseAssessmentRepository.findByUserCourseId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UserCourseAssessment not found"));

        return ResponseEntity.ok(userCourseAssessment);
    }

}

