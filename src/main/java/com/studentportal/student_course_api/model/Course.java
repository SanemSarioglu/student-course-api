package com.studentportal.student_course_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_code", length = 20)
    private String courseCode;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "credits", precision = 3, scale = 1)
    private BigDecimal credits;

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

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

