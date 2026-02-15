package com.uniflix.api.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String id) {
        super("Course not found with id: " + id);
    }
}
