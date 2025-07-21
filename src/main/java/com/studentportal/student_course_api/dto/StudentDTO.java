package com.studentportal.student_course_api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String semester;
    private String departmentCode; // Only the foreign key, not the full Department object
}
