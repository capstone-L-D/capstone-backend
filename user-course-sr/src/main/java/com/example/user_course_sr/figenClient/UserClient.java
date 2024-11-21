package com.example.user_course_sr.figenClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authentication-server", url = "http://localhost:7070/api/auth")
public interface UserClient {
    @GetMapping("/mail/{userId}")
    public String getUserMail(@PathVariable String userId);
}
