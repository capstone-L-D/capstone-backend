package com.example.user_answer.controller;

import com.example.user_answer.model.UserAnswer;
import com.example.user_answer.repo.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-answers")
public class UserAnswerController {
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @PostMapping
    public UserAnswer submitAnswer(@RequestBody UserAnswer userAnswer) {
        // Validate selected option with Assessment Service (via REST API)
        boolean isCorrect = validateAnswer(userAnswer.getQuestionId(), userAnswer.getSelectedOptionId());
        userAnswer.setIsCorrect(isCorrect);
        return userAnswerRepository.save(userAnswer);
    }

    private boolean validateAnswer(String questionId, String selectedOptionId) {
        // Call Assessment Service to fetch the correct answer for the question
        // Example implementation with dummy logic:
        return true; // Replace with actual validation logic
    }
}

