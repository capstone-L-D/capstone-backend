package com.example.Course_service.repo;

import com.example.Course_service.model.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> findByCourseIdIn(List<String> courseIds);
}
