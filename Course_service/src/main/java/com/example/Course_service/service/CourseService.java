package com.example.Course_service.service;

import com.example.Course_service.model.Course;
import com.example.Course_service.repo.CourseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;





@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

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
}
