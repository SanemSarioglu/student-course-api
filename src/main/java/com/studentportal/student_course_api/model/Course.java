package com.studentportal.student_course_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;



@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_code", length = 20)
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public BigDecimal getCredits() {
        return credits;
    }

    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Department getMajorDepartment() {
        return majorDepartment;
    }

    public void setMajorDepartment(Department majorDepartment) {
        this.majorDepartment = majorDepartment;
    }

    public String getAvailableForSemester() {
        return availableForSemester;
    }

    public void setAvailableForSemester(String availableForSemester) {
        this.availableForSemester = availableForSemester;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

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

