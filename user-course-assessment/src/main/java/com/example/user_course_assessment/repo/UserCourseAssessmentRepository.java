package com.example.user_course_assessment.repo;

import com.example.user_course_assessment.model.UserCourseAssessment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseAssessmentRepository extends MongoRepository<UserCourseAssessment, String> {
}

