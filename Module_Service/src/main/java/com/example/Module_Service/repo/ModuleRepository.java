package com.example.Module_Service.repo;

import com.example.Module_Service.model.Modules;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository public interface ModuleRepository extends MongoRepository<Modules, String> {
//    List<Modules> findByIdIn(List<ObjectId> moduleIds);

    List<Modules> findByModuleIdIn(List<String> moduleIds);
}
