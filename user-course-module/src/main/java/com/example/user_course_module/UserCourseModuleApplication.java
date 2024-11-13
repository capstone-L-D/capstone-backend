package com.example.user_course_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserCourseModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCourseModuleApplication.class, args);
	}

}
