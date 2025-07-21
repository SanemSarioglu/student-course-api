package com.studentportal.student_course_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {
    private Integer instructorId;
    private String firstName;
    private String lastName;
    private String email;
}