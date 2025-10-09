package com.barbosa.dscommerse.service.serviceException;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Resource not found. id: " + id);
    }
}
