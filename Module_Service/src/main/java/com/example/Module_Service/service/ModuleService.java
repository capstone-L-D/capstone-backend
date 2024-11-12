package com.example.Module_Service.service;

import com.example.Module_Service.dto.ContentDto;
import com.example.Module_Service.dto.ModuleWithContentDTO;
import com.example.Module_Service.feignClient.ContentClient;
import com.example.Module_Service.model.Modules;
import com.example.Module_Service.repo.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // Create a new module
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
    public List<Modules> findModulesByIds(List<String> moduleIds) {
        return moduleRepository.findByModuleIdIn(moduleIds);
    }
}
