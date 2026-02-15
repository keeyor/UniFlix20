package com.uniflix.api.exceptions;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String id) {
        super("Department not found with id: " + id);
    }
}
