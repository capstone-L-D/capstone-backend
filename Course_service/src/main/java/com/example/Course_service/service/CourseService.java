package com.example.Course_service.service;

import com.example.Course_service.dto.CourseModuleDto;
import com.example.Course_service.dto.InputDto;
import com.example.Course_service.feignClient.CourseModuleClient;
import com.example.Course_service.model.Course;
import com.example.Course_service.repo.CourseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseModuleClient courseModuleClient;

    // Retrieve a course by courseId
    public Optional<Course> getCourseById(String courseId) {  // Changed to String courseId
        return courseRepository.findById(courseId);
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Create a new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update an existing course
    public Optional<Course> updateCourse(String courseId, Course updatedCourse) {  // Changed to String courseId
        return courseRepository.findById(courseId).map(existingCourse -> {
            existingCourse.setCourseTitle(updatedCourse.getCourseTitle());
            existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
            return courseRepository.save(existingCourse);
        });
    }

    // Delete a course by courseId
    public boolean deleteCourse(String courseId) {  // Changed to String courseId
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    public List<Course> getCoursesByIds(List<String> courseIds) {
        return courseRepository.findByCourseIdIn(courseIds);
    }

    public String createCourses(InputDto inputDtos) {
        Course course = new Course();
        course.setCourseTitle(inputDtos.getCourseTitle());
        course.setCourseDescription(inputDtos.getCourseDescription());
        course.setCourseDuration(inputDtos.getCourseDuration());
        course.setCourseCategory(inputDtos.getCourseCategory());
        course.setCourseLevel(inputDtos.getCourseLevel());
        course.setImgUrl(inputDtos.getImgUrl());

        Course savedCourse = courseRepository.save(course);
        List<String> moduleIds = inputDtos.getSelectedModules();

        // Save the content
        List<CourseModuleDto> courseModuleDtos = new ArrayList<>(); // Use a mutable list here
        moduleIds.forEach(moduleId -> {
            CourseModuleDto courseModuleDto = new CourseModuleDto();
            courseModuleDto.setCourseId(savedCourse.getCourseId());
            courseModuleDto.setModuleId(moduleId);
            courseModuleDtos.add(courseModuleDto); // Now this works without exceptions
        });

        ResponseEntity<List<CourseModuleDto>> res = courseModuleClient.createAssociations(courseModuleDtos);
        if (res.getStatusCode().is2xxSuccessful()) {
            return "Created";
        } else {
            throw new RuntimeException("Failed to create module with content");
        }
    }

}
