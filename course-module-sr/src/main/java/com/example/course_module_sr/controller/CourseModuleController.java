package com.example.course_module_sr.controller;





import com.example.course_module_sr.dto.ContentDto;
import com.example.course_module_sr.dto.ModuleWithContentDTO;
import com.example.course_module_sr.dto.Modules;
import com.example.course_module_sr.dto.Response;
import com.example.course_module_sr.model.CourseModuleRelation;
import com.example.course_module_sr.repo.CourseModuleRepo;
import com.example.course_module_sr.serice.CourseModuleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course-modules")
public class CourseModuleController {

    @Autowired
    private CourseModuleService courseModuleService;
    @Autowired
    private CourseModuleRepo courseModuleRepo;

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



//    @PostMapping("/modules")
//    public List<Response> getModulesByCourseIds(@RequestBody List<String> courseModuleIds) {
//
//        // Use only the first CourseModuleId from the list to retrieve courseId
//        Optional<CourseModuleRelation> optionalCourseModule = courseModuleRepo.findById(courseModuleIds.get(0));
//
//        if (optionalCourseModule.isEmpty()) {
//            throw new IllegalArgumentException("Invalid courseModuleId: " + courseModuleIds.get(0));
//        }
//
//        // Retrieve courseId from the first CourseModuleRelation
//        CourseModuleRelation initialCourseModuleRelation = optionalCourseModule.get();
//        String courseId = initialCourseModuleRelation.getCourseId();
//
//        // Fetch all modules for the specified courseId
//        List<ModuleWithContentDTO> modules = courseModuleService.getAllModulesForCourse(courseId);
//
//        // Map each module to a Response object with a unique courseModuleId based on courseId and moduleId
//        return modules.stream().map(module -> {
//            // Find the unique CourseModuleRelation for the given courseId and moduleId
//            Optional<CourseModuleRelation> courseModuleRelationOpt = courseModuleRepo.findByCourseIdAndModuleId(courseId, module.getModuleId());
//
//            // Ensure the CourseModuleRelation exists
//            if (courseModuleRelationOpt.isEmpty()) {
//                throw new IllegalArgumentException("No CourseModuleRelation found for courseId: " + courseId + " and moduleId: " + module.getModuleId());
//            }
//
//            CourseModuleRelation courseModuleRelation = courseModuleRelationOpt.get();
//
//            // Construct the response object
//            Response response = new Response();
//            response.setModuleId(module.getModuleId());
//            response.setModuleTitle(module.getModuleTitle());
//            response.setModuleDuration(module.getModuleDuration());
//            response.setCourseModuleId(courseModuleRelation.getCourseModuleId()); // Unique courseModuleId for each module
//            return response;
//        }).collect(Collectors.toList());
//    }
@PostMapping("/modules")
public List<Response> getModulesByCourseIds(@RequestBody List<String> courseModuleIds) {
    // Use only the first CourseModuleId from the list to retrieve courseId
    Optional<CourseModuleRelation> optionalCourseModule = courseModuleRepo.findById(courseModuleIds.get(0));

    if (optionalCourseModule.isEmpty()) {
        throw new IllegalArgumentException("Invalid courseModuleId: " + courseModuleIds.get(0));
    }

    // Retrieve courseId from the first CourseModuleRelation
    CourseModuleRelation initialCourseModuleRelation = optionalCourseModule.get();
    String courseId = initialCourseModuleRelation.getCourseId();

    // Fetch all modules with content for the specified courseId
    List<ModuleWithContentDTO> moduleWithContentList = courseModuleService.getAllModulesForCourse(courseId);

    // Map each ModuleWithContentDTO to a Response object with a unique courseModuleId based on courseId and moduleId
    return moduleWithContentList.stream().map(moduleWithContent -> {
        Modules module = moduleWithContent.getModule(); // Retrieve the module details
        List<ContentDto> contentList = moduleWithContent.getContentList(); // Retrieve the associated contents

        // Find the unique CourseModuleRelation for the given courseId and moduleId
        Optional<CourseModuleRelation> courseModuleRelationOpt =
                courseModuleRepo.findByCourseIdAndModuleId(courseId, module.getModuleId());

        // Ensure the CourseModuleRelation exists
        if (courseModuleRelationOpt.isEmpty()) {
            throw new IllegalArgumentException("No CourseModuleRelation found for courseId: " + courseId + " and moduleId: " + module.getModuleId());
        }

        CourseModuleRelation courseModuleRelation = courseModuleRelationOpt.get();

        // Construct the response object with module and content details
        Response response = new Response();
        response.setModuleId(module.getModuleId());
        response.setModuleTitle(module.getModuleTitle());
        response.setModuleDuration(module.getModuleDuration());
        response.setCourseModuleId(courseModuleRelation.getCourseModuleId()); // Unique courseModuleId for each module
        response.setContentList(contentList); // Set the list of contents

        return response;
    }).collect(Collectors.toList());
}

    // Get All Modules for a Specific Course
    @GetMapping("/courses/modules/{courseId}")
    public ResponseEntity<List<ModuleWithContentDTO>> getAllModulesForCourse(@PathVariable String courseId) {
        List<ModuleWithContentDTO> modules = courseModuleService.getAllModulesForCourse(courseId);
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

