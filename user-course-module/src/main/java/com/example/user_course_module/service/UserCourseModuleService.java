package com.example.user_course_module.service;

import com.example.user_course_module.dto.Modules;
import com.example.user_course_module.dto.ReceivedResponse;

import com.example.user_course_module.dto.Response;
import com.example.user_course_module.feignClient.CourseModuleClient;
import com.example.user_course_module.model.UserCourseModule;
import com.example.user_course_module.repo.UserCourseModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCourseModuleService {

    @Autowired
    private UserCourseModuleRepository repository;
    @Autowired
    private CourseModuleClient courseModuleClient;

    public UserCourseModule saveUserCourseModules(UserCourseModule userCourseModule) {
        return repository.save(userCourseModule);
    }

    public List<Response> getModulesByUserCourseId(String userCourseId) {
        // Retrieve the list of UserCourseModules associated with the given userCourseId
        List<UserCourseModule> userCourseModules = repository.findAllByUserCourseId(userCourseId);
        System.out.println(userCourseModules);

        // Collect courseModuleIds from the UserCourseModules
        List<String> courseModuleIds = userCourseModules.stream()
                .map(UserCourseModule::getCourseModuleId)
                .collect(Collectors.toList());

        // Fetch the modules by courseModuleIds from the external client
        List<ReceivedResponse> receivedResponses = courseModuleClient.getModulesByCourseIds(courseModuleIds);

        // Map each ReceivedResponse to the Response object with additional userCourseModule data
        return receivedResponses.stream().map(receivedResponse -> {

                    // Find the corresponding UserCourseModule by courseModuleId
                    UserCourseModule matchingUserCourseModule = userCourseModules.stream()
                            .filter(ucm -> ucm.getCourseModuleId().equals(receivedResponse.getCourseModuleId()))
                            .findFirst()
                            .orElse(null);

                    // If there's no match, skip this response
                    if (matchingUserCourseModule == null) {
                        return null;
                    }

                    // Create and populate the Response object
                    Response response = new Response();
                    response.setModuleId(receivedResponse.getModuleId());
                    response.setModuleTitle(receivedResponse.getModuleTitle());
                    response.setModuleDuration(receivedResponse.getModuleDuration());
                    response.setUserCourseModuleId(matchingUserCourseModule.getUserCourseModuleId()); // From UserCourseModule
                    response.setUserCourseId(matchingUserCourseModule.getUserCourseId()); // From UserCourseModule
                    response.setCourseModuleId(matchingUserCourseModule.getCourseModuleId()); // From UserCourseModule
                    response.setProgress(matchingUserCourseModule.getProgress()); // From UserCourseModule
                    response.setCompletionStatus(matchingUserCourseModule.getCompletionStatus()); // From UserCourseModule
                    response.setStartDate(matchingUserCourseModule.getStartDate()); // From UserCourseModule
                    response.setCompletionDate(matchingUserCourseModule.getCompletionDate()); // From UserCourseModule
                    response.setContentList(receivedResponse.getContentList()); // Content list from ReceivedResponse

                    return response;
                }).filter(response -> response != null) // Filter out null responses
                .collect(Collectors.toList());
    }




    public UserCourseModule updateModuleProgress(UserCourseModule updatedUserCourseModule) {

        Optional<UserCourseModule> existingModuleOpt = repository.findById(updatedUserCourseModule.getUserCourseModuleId());

        if (existingModuleOpt.isPresent()) {
            UserCourseModule existingModule = existingModuleOpt.get();


            existingModule.setProgress(updatedUserCourseModule.getProgress());
            existingModule.setCompletionStatus(updatedUserCourseModule.getCompletionStatus());
            existingModule.setStartDate(updatedUserCourseModule.getStartDate());
            existingModule.setCompletionDate(updatedUserCourseModule.getCompletionDate());


            return repository.save(existingModule);
        } else {
            return null;
        }
    }
}