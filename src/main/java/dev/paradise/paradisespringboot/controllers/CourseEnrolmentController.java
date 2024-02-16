package dev.paradise.paradisespringboot.controllers;

import dev.paradise.paradisespringboot.models.Course;
import dev.paradise.paradisespringboot.objects.CourseDTO;
import dev.paradise.paradisespringboot.objects.CourseEnrolmentDTO;
import dev.paradise.paradisespringboot.queryresults.CourseEnrolmentQueryResult;
import dev.paradise.paradisespringboot.requests.CourseEnrolmentRequest;
import dev.paradise.paradisespringboot.services.CourseEnrolmentService;
import dev.paradise.paradisespringboot.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrollments")
public class CourseEnrolmentController {
    private final CourseEnrolmentService courseEnrolmentService;
    private final LessonService lessonService;

    public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, LessonService lessonService) {
        this.courseEnrolmentService = courseEnrolmentService;
        this.lessonService = lessonService;
    }

    //Lấy tất cả khóa học người dùng đã đăng ký bằng username
    @GetMapping("/getAllEnrolledCoursesByUsername")
    public ResponseEntity<List<CourseDTO>> enrollments(Principal principal) {
        List<Course> courses = courseEnrolmentService.getAllEnrolledCoursesByUsername(principal.getName());

        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO();

                    responseCourse.setIdentifier(course.getIdentifier());
                    responseCourse.setTitle(course.getTitle());
                    responseCourse.setTeacher(course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    responseCourse.setEnrolled(true);

                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    //Đăng ký khóa học
    @PostMapping("/")
    public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest request, Principal principal) {
        //request CourseEnrolmentRequest bị lỗi
        System.out.println("zzzzz:"+request.getIdentifier());
        //CourseEnrolmentQueryResult enrolmentQueryResult = courseEnrolmentService.enrollIn(principal.getName(), "PL4LFuHwItvKbdK-ogNsOx2X58hHGeQm8c");
        CourseEnrolmentQueryResult enrolmentQueryResult = courseEnrolmentService.enrollIn(principal.getName(), request.getIdentifier());

        CourseEnrolmentDTO responseEnrolment = new CourseEnrolmentDTO();

        responseEnrolment.setName(enrolmentQueryResult.getUser().getName());
        responseEnrolment.setUsername(enrolmentQueryResult.getUser().getUsername());
        responseEnrolment.setCourse(enrolmentQueryResult.getCourse());

        return new ResponseEntity<>(responseEnrolment, HttpStatus.OK);
    }
}