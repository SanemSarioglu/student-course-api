package com.studentportal.student_course_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {
    private Integer sectionId;
    private String courseCode; // Only the foreign key
    private String semester;
    private Integer year;
    private Integer instructorId; // Only the foreign key
    private Integer capacity;
    private Integer currentEnrollment;
    private String status;
    private String sectionNumber;
}
