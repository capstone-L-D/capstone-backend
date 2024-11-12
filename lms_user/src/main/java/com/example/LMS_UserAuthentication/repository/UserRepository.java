package com.example.LMS_UserAuthentication.repository;

import com.example.LMS_UserAuthentication.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {
    public Optional<User> findByUserName(String name);
}
