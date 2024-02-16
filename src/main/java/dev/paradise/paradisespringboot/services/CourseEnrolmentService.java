package dev.paradise.paradisespringboot.services;

import dev.paradise.paradisespringboot.models.Course;
import dev.paradise.paradisespringboot.queryresults.CourseEnrolmentQueryResult;
import dev.paradise.paradisespringboot.repositories.CourseRepository;
import dev.paradise.paradisespringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrolmentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseEnrolmentService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username) {
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }

    public boolean getEnrolmentStatus(String username, String courseIdentifier) {
        return userRepository.findEnrolmentStatus(username, courseIdentifier);
    }

    public CourseEnrolmentQueryResult enrollIn(String username, String courseIdentifier) {
        // TODO: make sure that the user has not been enrolled in the course already.
        //phải đảm bảo kiểm tra user chưa đăng ký khóa chưa bằng getEnrolmentStatus
        return userRepository.createEnrolmentRelationship(username, courseIdentifier);
    }
}
