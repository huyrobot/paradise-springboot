package dev.paradise.paradisespringboot.services;

import dev.paradise.paradisespringboot.models.Course;
import dev.paradise.paradisespringboot.repositories.CourseRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    //Hàm lấy tất cả khóa học (hàm này đơn giản nên thư viện hỗ trợ sẵn)
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    //Hàm này tự viết nhưng không rõ nó tìm identifier bằng cách nào
    public Course getCourseByIdentifier(String identifier){
        return courseRepository.findCourseByIdentifier(identifier)
                .orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }
}
