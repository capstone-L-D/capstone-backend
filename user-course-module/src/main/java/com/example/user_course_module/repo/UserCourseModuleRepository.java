package com.example.user_course_module.repo;

import com.example.user_course_module.model.UserCourseModule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCourseModuleRepository extends  MongoRepository<UserCourseModule, String>{

    List<UserCourseModule> findByUserCourseId(String userCourseId);

    List<UserCourseModule> findAllByUserCourseId(String userCourseId);
}
