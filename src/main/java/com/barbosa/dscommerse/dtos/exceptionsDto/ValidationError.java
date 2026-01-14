package com.barbosa.dscommerse.dtos.exceptionsDto;

import com.barbosa.dscommerse.controllers.controllerException.CustomError;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends CustomError {

    private final List<FieldMessage> fieldErrors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String fieldName, String message) {
        fieldErrors.removeIf(e -> e.getFieldName().equals(fieldName));
        fieldErrors.add(new FieldMessage(fieldName, message));
    }
}
