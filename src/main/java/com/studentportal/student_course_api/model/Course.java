package com.studentportal.student_course_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @Column(name = "course_code", length = 20)
    private String courseCode;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "credits", nullable = false, precision = 3, scale = 1)
    private Double credits;

    @Column(name = "prerequisites")
    private String prerequisites;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_code", referencedColumnName = "department_code")
    private Department majorDepartment;

    @Column(name = "available_for_semester", length = 50)
    private String availableForSemester;

    @Column(name = "instructor", length = 100)
    private String instructor;
}

