package com.uniflix.api.exceptions;

public class SchoolNotFoundException extends RuntimeException {

    public SchoolNotFoundException(String id) {
        super("School not found with id: " + id);
    }
}
