package com.studentportal.student_course_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private String departmentCode;
    private String departmentName;
    private String headOfDepartment;
}

