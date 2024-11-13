package com.example.user_course_sr.service;

import com.example.user_course_sr.dto.Course;
import com.example.user_course_sr.dto.Response;
import com.example.user_course_sr.figenClient.CourseClient;
import com.example.user_course_sr.model.UserCourse;
import com.example.user_course_sr.repo.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;

@Service
public class UserCourseService {
    @Autowired
    private  UserCourseRepository userCourseRepository;

    @Autowired
    private CourseClient courseClient;


    public UserCourse enrollUserInCourse(String userCourseId, String userId, String courseId) {
        UserCourse userCourse = new UserCourse();
        userCourse.setUserCourseId(userCourseId);
        userCourse.setUserId(userId);
        userCourse.setCourseId(courseId);
        userCourse.setEnrollmentDate(LocalDate.now());
        userCourse.setProgress(0.0);
        userCourse.setIsCompleted(false);
        return userCourseRepository.save(userCourse);
    }

    public List<UserCourse> getUserCourses(String userId) {
        return userCourseRepository.findByUserId(userId);
    }

//    public void updateProgress(String userCourseId, Double progress) {
//        UserCourse userCourse = userCourseRepository.findById(userCourseId)
//                .orElseThrow(() -> new RuntimeException("UserCourse not found"));
//        userCourse.setProgress(progress);
//
//        if (progress >= 100.0) {
//            userCourse.setIsCompleted(true);
//            userCourse.setCompletionDate(LocalDate.now());
//        }
//
//        userCourseRepository.save(userCourse);
//    }

    public List<UserCourse> getCompletedCoursesByUser(String userId) {
        return userCourseRepository.findByUserIdAndIsCompleted(userId, true);
    }

    public List<Response> getCoursesForUser(String userId) {
        // Retrieve the list of UserCourse objects for the user
        List<UserCourse> userCourses = userCourseRepository.findByUserId(userId);

        // Extract courseIds from the userCourses
        List<String> courseIds = userCourses.stream()
                .map(UserCourse::getCourseId)
                .toList();

        // Fetch Course details for each courseId
        List<Course> courses = courseClient.getCoursesByIds(courseIds);

        // Map Course and UserCourse objects to create the Response list
        return courses.stream().map(course -> {
            // Find the matching UserCourse object for the current course
            UserCourse userCourse = userCourses.stream()
                    .filter(uc -> uc.getCourseId().equals(course.getCourseId()))
                    .findFirst()
                    .orElse(null);

            // Build and return a Response object with combined information
            return new Response(
                    course.getCourseId(),
                    course.getCourseTitle(),
                    course.getCourseDescription(),
                    course.getCourseDuration(),
                    course.getCourseLevel(),
                    course.getCourseCategory(),
                    course.getImgUrl(),
                    userCourse != null ? userCourse.getUserCourseId() : null,
                    userCourse != null ? userCourse.getEnrollmentDate() : null,
                    userCourse != null ? userCourse.getProgress() : null,
                    userCourse != null ? userCourse.getCompletionDate() : null,
                    userCourse != null ? userCourse.getIsCompleted() : null
            );
        }).toList();
    }

}

