package dev.paradise.paradisespringboot.requests;

public class CourseEnrolmentRequest {
    private String identifier;

    public CourseEnrolmentRequest() {
    }

//    public CourseEnrolmentRequest(String courseIdentifier) {
//        this.courseIdentifier = courseIdentifier;
//    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
