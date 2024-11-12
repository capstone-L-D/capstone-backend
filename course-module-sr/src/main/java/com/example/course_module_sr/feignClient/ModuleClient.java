package com.example.course_module_sr.feignClient;

import com.example.course_module_sr.dto.Modules;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "module-service", url = "http://localhost:7074/modules")
public interface ModuleClient {
    @PostMapping("/batch")
    List<Modules> getModulesByIds(@RequestBody List<String> moduleIds);
}
