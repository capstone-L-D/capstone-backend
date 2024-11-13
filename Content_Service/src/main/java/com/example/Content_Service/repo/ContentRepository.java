package com.example.Content_Service.repo;

import com.example.Content_Service.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content,String> {
    List<Content> findByModuleId(String moduleId);
}
