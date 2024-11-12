package com.example.course_module_sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CourseModuleSrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseModuleSrApplication.class, args);
	}

}
