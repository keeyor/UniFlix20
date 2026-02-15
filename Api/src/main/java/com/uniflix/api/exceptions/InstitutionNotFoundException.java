package com.uniflix.api.exceptions;

public class InstitutionNotFoundException extends RuntimeException {

    public InstitutionNotFoundException(String id) {
        super("Institution not found with id: " + id);
    }
}
