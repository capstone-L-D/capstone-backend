package com.example.LMS_feedback.controller;

import com.example.LMS_feedback.model.Feedback;

import com.example.LMS_feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @GetMapping("/getFeedback/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable String id) {
        Feedback feedback = service.getFeedbackById(id);
        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/getAllFeedback")
    public ResponseEntity<List<Feedback>> getallfeedback() {
        List<Feedback> li = service.getAllFeedback();
        if (li != null) {
            return ResponseEntity.ok(li);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createFeedback")
    public ResponseEntity<Feedback> createfeedback(@RequestBody Feedback feedback) {
        Feedback f = service.createFeedback(feedback);

        return ResponseEntity.ok(f);

    }
    @PutMapping("/updateFeedback/{id}")
    public ResponseEntity<Feedback> updatefeedback(@PathVariable String id,@RequestBody  Feedback feedback){
        Feedback f=service.updateFeedback(id,feedback);
        return ResponseEntity.ok(f);
    }
    @DeleteMapping("/deleteFeedback/{id}")
    public ResponseEntity<Feedback> deletefeedback(@PathVariable String id){
        service.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/createBatchFeedback")
    public ResponseEntity<List<Feedback>> createbatchfeedback(@RequestBody List<Feedback> feedback) {
        List<Feedback> f = service.createbatchFeedback(feedback);

        return ResponseEntity.ok(f);

    }

//    @GetMapping("/getFeedbackByUC/{id}")
//    public ResponseEntity<Feedback> getFeedbackByUserCourse(@PathVariable String id) {
//        Feedback feedback = service.getfeedbackbyusercourseid(id);
//        if (feedback != null) {
//            return ResponseEntity.ok(feedback);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
//    @GetMapping("/getFeedbackByCourse/{id}")
//    public ResponseEntity<List<Feedback>> getFeedbackByCourse(@PathVariable String id) {
//        List<Feedback> feedback = service.getfeedbackbycourseid(id);
//        if (feedback != null) {
//            return ResponseEntity.ok(feedback);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }


}
