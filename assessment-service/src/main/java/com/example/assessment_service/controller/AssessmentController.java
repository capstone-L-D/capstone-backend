package com.example.assessment_service.controller;


import com.example.assessment_service.model.Assessment;
import com.example.assessment_service.model.Question;
import com.example.assessment_service.repo.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assessments")
@CrossOrigin
public class AssessmentController {

    @Autowired
    private AssessmentRepository assessmentRepository;

    /**
     * Get all assessments
     */
    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    /**
     * Get a specific assessment by ID
     */
    @GetMapping("/{id}")
    public Assessment getAssessment(@PathVariable String id) {
        return assessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found"));
    }

    /**
     * Create a new assessment
     */
    @PostMapping
    public Assessment createAssessment(@RequestBody Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    /**
     * Update an existing assessment
     */
    @PutMapping("/{id}")
    public Assessment updateAssessment(@PathVariable String id, @RequestBody Assessment updatedAssessment) {
        Assessment existingAssessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found"));

        existingAssessment.setTitle(updatedAssessment.getTitle());
        existingAssessment.setTotalScore(updatedAssessment.getTotalScore());
        existingAssessment.setPassScore(updatedAssessment.getPassScore());
        existingAssessment.setTimeLimit(updatedAssessment.getTimeLimit());
        existingAssessment.setQuestions(updatedAssessment.getQuestions());

        return assessmentRepository.save(existingAssessment);
    }

    /**
     * Delete an assessment
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssessment(@PathVariable String id) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found"));

        assessmentRepository.delete(assessment);
        return ResponseEntity.ok("Assessment deleted successfully");
    }

    @PostMapping("/{id}/questions")
    public Assessment addQuestion(@PathVariable String id, @RequestBody Question question) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found"));

        List<Question> questions = assessment.getQuestions();
        if (questions == null) {
            questions = new ArrayList<>();
        }

        questions.add(question);
        assessment.setQuestions(questions);

        return assessmentRepository.save(assessment);
    }

    /**
     * Get all questions for a specific assessment
     */
    @GetMapping("/{id}/questions")
    public List<Question> getQuestions(@PathVariable String id) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assessment not found"));

        return assessment.getQuestions();
    }

    @GetMapping("/course/{courseId}")
    public Assessment getAssessmentsByCourseId(@PathVariable String courseId) {
        return assessmentRepository.findByCourseId(courseId);
    }
}

