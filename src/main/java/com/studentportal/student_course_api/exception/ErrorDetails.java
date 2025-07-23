// src/main/java/com/studentportal/student_course_api/exceptions/ErrorDetails.java
package com.studentportal.student_course_api.exception;

import java.util.Date; // For the timestamp


/**
 * ErrorDetails is a Data Transfer Object (DTO) that encapsulates the details of an error
 * that occurs in the application. It is used to provide structured information about errors
 * to the client, including a timestamp, a message, and additional details.
 *
 * This class is typically used in conjunction with exception handling mechanisms in Spring,
 * such as @ControllerAdvice, to return meaningful error responses.
 */

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details; // This could be the request URI, a stack trace (for debugging), etc.

    // Constructor to easily create an instance of ErrorDetails
    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // --- Getters (Manually added since you're not using Lombok) ---
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    // --- Setters (Typically not needed for an immutable error DTO, but can be added if required) ---
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}