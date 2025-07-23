package com.studentportal.student_course_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a centralized exception handling class for the application.
 * It uses the {@code @ControllerAdvice} annotation to handle exceptions globally across all controllers.
 * This class provides specific handlers for custom exceptions, validation errors, and general exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} and returns a structured error response.
     *
     * @param ex      the exception thrown when a resource is not found
     * @param request the web request that resulted in the exception
     * @return a {@link ResponseEntity} containing an {@link ErrorDetails} object and HTTP 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all uncaught exceptions and returns a structured error response.
     *
     * @param ex      the exception that was not explicitly handled
     * @param request the web request that resulted in the exception
     * @return a {@link ResponseEntity} containing an {@link ErrorDetails} object and HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles validation errors (e.g., {@code @Valid} annotation failures) and returns a map of field-error pairs.
     *
     * @param ex the exception thrown when validation fails
     * @return a {@link ResponseEntity} containing a map of validation errors and HTTP 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}