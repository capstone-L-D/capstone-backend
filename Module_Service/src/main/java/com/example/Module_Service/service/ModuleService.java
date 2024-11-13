package com.example.Module_Service.service;

import com.example.Module_Service.dto.ContentDto;
import com.example.Module_Service.dto.ModuleWithContentDTO;
import com.example.Module_Service.feignClient.ContentClient;
import com.example.Module_Service.model.Modules;
import com.example.Module_Service.repo.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ContentClient contentClient;

    // Retrieve a module by ID
    public Optional<Modules> getModuleById(String id) {
        return moduleRepository.findById(id);
    }

    // Retrieve a module with its associated content
    public ModuleWithContentDTO getModuleWithContent(String moduleId) {
        Modules module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        List<ContentDto> contentList = contentClient.getContentByModuleId(moduleId);

        return new ModuleWithContentDTO(module, contentList);
    }


    public Modules createModule(Modules module) {
        return moduleRepository.save(module);
    }

    // Update an existing module
    public Modules updateModule(String id, Modules updatedModule) {
        return moduleRepository.findById(id).map(existingModule -> {
            return moduleRepository.save(existingModule);
        }).orElseThrow(() -> new RuntimeException("Module not found"));
    }

    // Delete a module by ID
    public void deleteModule(String id) {
        if (moduleRepository.existsById(id)) {
            moduleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Module not found");
        }
    }

    // Find multiple modules by their IDs
    public List<ModuleWithContentDTO> findModulesByIds(List<String> moduleIds) {
        // Step 1: Fetch all modules using module repository
        List<Modules> modules = moduleRepository.findByModuleIdIn(moduleIds);

        // Step 2: Create a list to hold the resulting ModuleWithContentDTOs
        List<ModuleWithContentDTO> moduleWithContentDTOList = new ArrayList<>();

        // Step 3: Iterate over each module
        for (Modules module : modules) {
            // Fetch content list for each module
            List<ContentDto> contentList = contentClient.getContentByModuleId(module.getModuleId());

            // Create a new ModuleWithContentDTO for the module and set its content
            ModuleWithContentDTO moduleWithContentDTO = new ModuleWithContentDTO();
            moduleWithContentDTO.setModule(module);         // Set the module data
            moduleWithContentDTO.setContentList(contentList); // Set the content list

            // Add to the result list
            moduleWithContentDTOList.add(moduleWithContentDTO);
        }

        // Step 4: Return the list of ModuleWithContentDTOs
        return moduleWithContentDTOList;
    }

}
