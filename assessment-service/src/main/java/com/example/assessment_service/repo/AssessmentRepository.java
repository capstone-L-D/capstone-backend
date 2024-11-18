package com.example.assessment_service.repo;

import com.example.assessment_service.model.Assessment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssessmentRepository extends MongoRepository<Assessment,String> {
    Assessment findByCourseId(String courseId);
}
