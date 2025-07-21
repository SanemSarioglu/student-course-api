package com.studentportal.student_course_api.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Integer enrollmentId;
    private Integer studentId; // Only the foreign key
    private Integer sectionId; // Only the foreign key
    private LocalDateTime enrollmentDate;
    private String grade;
    private String status;
}
