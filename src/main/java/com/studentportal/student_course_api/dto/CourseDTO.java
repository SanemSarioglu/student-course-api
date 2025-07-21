package com.studentportal.student_course_api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private String courseCode;
    private String courseName;
    private Double credits;
    private String prerequisites;
    private Boolean isActive;
    private String majorCode; // Only the foreign key, not the full Department object
    private String availableForSemester;
    private String instructor;
}
