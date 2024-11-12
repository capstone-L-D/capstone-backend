package com.example.course_module_sr.controller;





import com.example.course_module_sr.dto.Modules;
import com.example.course_module_sr.model.CourseModuleRelation;
import com.example.course_module_sr.serice.CourseModuleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/course-modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService courseModuleService;

    // Associate a Module with a Course
    @PostMapping
    public ResponseEntity<CourseModuleRelation> associateModuleWithCourse(
            @RequestBody CourseModuleRelation relation) {

        relation.setCreatedAt(LocalDateTime.now().toString());
        CourseModuleRelation createdRelation = courseModuleService.createAssociation(relation.getCourseModuleId(),
                relation.getCourseId(), relation.getModuleId(), relation.getCreatedAt());
        return ResponseEntity.ok(createdRelation);
    }

    // Remove Association between a Module and a Course
    @DeleteMapping
    public ResponseEntity<Void> removeAssociation(
            @RequestParam String courseTitle, @RequestParam String moduleTitle) {
        courseModuleService.removeAssociation(courseTitle, moduleTitle);
        return ResponseEntity.noContent().build();
    }

    // Get All Modules for a Specific Course
    @GetMapping("/courses/modules/{courseId}")
    public ResponseEntity<List<Modules>> getAllModulesForCourse(@PathVariable String courseId) {
        List<Modules> modules = courseModuleService.getAllModulesForCourse(courseId);
        return ResponseEntity.ok(modules);
    }

    // Get All Courses for a Specific Module
//    @GetMapping("/modules/courses/{module}")
//    public ResponseEntity<List<CourseModuleRelation>> getAllCoursesForModule(@PathVariable String  moduleTitle) {
//        List<CourseModuleRelation> courses = courseModuleService.getAllCoursesForModule(moduleTitle);
//        return ResponseEntity.ok(courses);
//    }

    // List All Course-Module Relationships
    @GetMapping
    public ResponseEntity<List<CourseModuleRelation>> getAllRelations() {
        List<CourseModuleRelation> relations = courseModuleService.getAllRelations();
        return ResponseEntity.ok(relations);
    }
}

