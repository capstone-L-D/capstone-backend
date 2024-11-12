package com.example.course_module_sr.repo;

import com.example.course_module_sr.model.CourseModuleRelation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseModuleRepo  extends MongoRepository<CourseModuleRelation,String> {
    void deleteByCourseIdAndModuleId(String courseTitle, String moduleTitle);

    List<CourseModuleRelation> findByCourseId(String courseTitle);

    List<CourseModuleRelation> findByModuleId(String moduleTitle);
}
