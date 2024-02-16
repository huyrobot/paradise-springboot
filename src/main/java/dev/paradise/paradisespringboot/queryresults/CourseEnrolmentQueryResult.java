package dev.paradise.paradisespringboot.queryresults;

import dev.paradise.paradisespringboot.models.Course;
import dev.paradise.paradisespringboot.models.User;

public class CourseEnrolmentQueryResult {
    private User user;
    private Course course;

    public CourseEnrolmentQueryResult() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}