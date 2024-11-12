package com.example.Content_Service.service;

import com.example.Content_Service.model.Content;
import com.example.Content_Service.repo.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getContentByModuleId(Long moduleId) {
        return contentRepository.findByModuleId(moduleId);
    }

    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    public Content createContent(Content content) {
        return contentRepository.save(content);
    }

    public Optional<Content> updateContent(Long id, Content updatedContent) {
        return contentRepository.findById(id).map(existingContent -> {

            return contentRepository.save(existingContent);
        });
    }

    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
