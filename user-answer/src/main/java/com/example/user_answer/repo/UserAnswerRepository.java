package com.example.user_answer.repo;

import com.example.user_answer.model.UserAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends MongoRepository<UserAnswer, String> {
}
