package com.example.course_module_sr.repo;

import com.example.course_module_sr.model.CourseModuleRelation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CourseModuleRepo  extends MongoRepository<CourseModuleRelation,String> {
    void deleteByCourseIdAndModuleId(String courseTitle, String moduleTitle);

    List<CourseModuleRelation> findByCourseId(String courseTitle);

    List<CourseModuleRelation> findByModuleId(String moduleTitle);

    Optional<CourseModuleRelation> findByCourseIdAndModuleId(String courseId, String moduleId);
}
