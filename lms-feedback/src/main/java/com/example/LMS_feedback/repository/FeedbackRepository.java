package com.example.LMS_feedback.repository;


import com.example.LMS_feedback.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback,String> {
    Optional<Feedback> findByFeedbackId(String feedbackId);
    Optional<Feedback> findByUserCourseId(String userCourseId);

//    List<Feedback> findByCourseId(String id);
}
