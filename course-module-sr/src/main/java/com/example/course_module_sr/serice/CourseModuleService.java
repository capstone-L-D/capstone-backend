package com.example.course_module_sr.serice;



import com.example.course_module_sr.dto.ModuleWithContentDTO;
import com.example.course_module_sr.dto.Modules;
import com.example.course_module_sr.feignClient.ModuleClient;
import com.example.course_module_sr.model.CourseModuleRelation;
import com.example.course_module_sr.repo.CourseModuleRepo;
import com.mongodb.internal.VisibleForTesting;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseModuleService {

    @Autowired
    private CourseModuleRepo courseModuleRelationRepository;

    @Autowired
    private ModuleClient moduleClient;

    public CourseModuleRelation createAssociation(String courseModuleId ,String courseTitle, String moduleTitle, String createdAt) {
        CourseModuleRelation relation = new CourseModuleRelation(courseModuleId,courseTitle, moduleTitle, createdAt);
        return courseModuleRelationRepository.save(relation);
    }

    public void removeAssociation(String courseTitle, String moduleTitle) {
        courseModuleRelationRepository.deleteByCourseIdAndModuleId(courseTitle, moduleTitle);
    }

    public List<ModuleWithContentDTO> getAllModulesForCourse(String courseId) {
        List<String> moduleIds = courseModuleRelationRepository.findByCourseId(courseId)
                .stream()
                .map(CourseModuleRelation::getModuleId)
                .toList();
        return moduleClient.getModulesByIds(moduleIds);

    }



    public List<CourseModuleRelation> getAllCoursesForModule(String moduleTitle) {
        return courseModuleRelationRepository.findByModuleId(moduleTitle);
    }

    public List<CourseModuleRelation> getAllRelations() {
        return courseModuleRelationRepository.findAll();
    }


    public List<CourseModuleRelation> createAssociations(List<CourseModuleRelation> relations) {
        return courseModuleRelationRepository.saveAll(relations);
    }

    public List<String> getCourseModulesByCourseId(String courseId) {
        return courseModuleRelationRepository.findByCourseId(courseId)
                .stream()
                .map(CourseModuleRelation::getCourseModuleId)
                .toList();

    }
}

