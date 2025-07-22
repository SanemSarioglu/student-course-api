package com.studentportal.student_course_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Id
    @Column(name = "course_code", length = 20)
    private String courseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_code", referencedColumnName = "department_code", nullable = false)
    private Department department; // Maps to major_code

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "credits", nullable = false, precision = 10, scale = 2)
    private BigDecimal credits;

    @Column(name = "prerequisites", columnDefinition = "TEXT")
    private String prerequisites;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "available_for_semester", length = 50)
    private String availableForSemester;

    @Column(name = "instructor", length = 100) // This might be better as a relationship to Instructor entity
    private String instructor; // Consider changing to @ManyToOne with Instructor entity

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;
}