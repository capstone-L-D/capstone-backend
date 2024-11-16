package com.example.Module_Service.controller;

import com.example.Module_Service.dto.InputDto;
import com.example.Module_Service.dto.ModuleWithContentDTO;
import com.example.Module_Service.model.Modules;
import com.example.Module_Service.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @GetMapping
    public List<Modules> getAllModules() {
        return moduleService.getAllModules();
    }

    // Get module by ID
    @GetMapping("/{id}")
    public ResponseEntity<Modules> getModuleById(@PathVariable String id) {
        return moduleService.getModuleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get module with content by ID
    @GetMapping("/content/{id}")
    public ResponseEntity<ModuleWithContentDTO> getModuleWithContent(@PathVariable String id) {
        return ResponseEntity.ok(moduleService.getModuleWithContent(id));
    }

    // Create a new module
    @PostMapping
    public Modules createModule(@RequestBody Modules module) {
        return moduleService.createModule(module);
    }

    // Batch request to retrieve multiple modules by IDs
    @PostMapping("/batch")
    public List<ModuleWithContentDTO> getModulesByIds(@RequestBody List<String> moduleIds) {
        return moduleService.findModulesByIds(moduleIds);
    }

    // Update a module
    @PutMapping("/{id}")
    public ResponseEntity<Modules> updateModule(@PathVariable String id, @RequestBody Modules updatedModule) {
        return ResponseEntity.ok(moduleService.updateModule(id, updatedModule));
    }

    // Delete a module
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable String id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createModuleWithContent(@RequestBody InputDto inputDto) {

        return ResponseEntity.ok(moduleService.createModuleWithContent(inputDto));
    }
}
