// src/main/java/com/studentportal/student_course_api/exceptions/ResourceNotFoundException.java
package com.studentportal.student_course_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to handle cases where a requested resource is not found.
 * This exception can be thrown when a specific entity (like a student, course, etc.)
 * cannot be found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // When this exception is thrown, Spring will return a 404 Not Found status
public class ResourceNotFoundException extends RuntimeException {

    // Constructor that takes a message
    public ResourceNotFoundException(String message) {
        super(message); // Calls the constructor of the parent (RuntimeException)
    }

    // Constructor that takes a message and a cause (the original exception)
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause); // Calls the constructor of the parent (RuntimeException)
    }
}