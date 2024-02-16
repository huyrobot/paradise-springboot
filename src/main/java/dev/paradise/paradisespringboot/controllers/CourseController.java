package dev.paradise.paradisespringboot.controllers;

import dev.paradise.paradisespringboot.models.Course;
import dev.paradise.paradisespringboot.objects.CourseDTO;
import dev.paradise.paradisespringboot.services.CourseEnrolmentService;
import dev.paradise.paradisespringboot.services.CourseService;
import dev.paradise.paradisespringboot.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//request chung
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseEnrolmentService courseEnrolmentService;
    //constructor


    public CourseController(CourseService courseService, LessonService lessonService, CourseEnrolmentService courseEnrolmentService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseEnrolmentService = courseEnrolmentService;
    }

    //controller lấy tất cả khóa học
    @GetMapping("/")
    public ResponseEntity<List<Course>> courseIndex(){
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }
    //controller lấy tất cả khóa học kèm theo relationship lesson
    @GetMapping("/all-course-lesson")
    public ResponseEntity<List<CourseDTO>> courseIndex2(Principal principal){
        //Lấy tất cả khóa học
        List<Course> courses = courseService.getAllCourses();
        //Tạo mảng responseCourses bằng cách duyệt từng khóa học courses(sử dụng map),
        // sau đó gán danh sách lesson cho từng khóa học
        List<CourseDTO> responseCourses = courses.stream().map(
                (course) ->{
                    CourseDTO responseCourse = new CourseDTO();

                    responseCourse.setIdentifier(course.getIdentifier());
                    responseCourse.setTitle(course.getTitle());
                    responseCourse.setTeacher(course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));

                    if (principal != null)
                        responseCourse.setEnrolled(courseEnrolmentService.getEnrolmentStatus(principal.getName(),
                                course.getIdentifier()));
                    return responseCourse;
                }
        ).toList();
        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }
    //controller lấy course theo identifier
    @GetMapping("/{identifier}")
    public ResponseEntity<CourseDTO> courseDetails(@PathVariable String identifier, Principal principal){
        //lấy course theo identifier
        Course course = courseService.getCourseByIdentifier(identifier);
        //Khai báo CourseDTO để lấy dữ liệu cần thiết từ Course
        CourseDTO responseCourse = new CourseDTO();

        //lấy tất cả các lesson theo identifier để set cho responseCourse
        responseCourse.setIdentifier(course.getIdentifier());
        responseCourse.setTitle(course.getTitle());
        responseCourse.setTeacher(course.getTeacher());
        responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(identifier));

        if (principal != null)
            responseCourse.setEnrolled(courseEnrolmentService.getEnrolmentStatus(principal.getName(), identifier));

        return new ResponseEntity<>(responseCourse, HttpStatus.OK);
    }
}
