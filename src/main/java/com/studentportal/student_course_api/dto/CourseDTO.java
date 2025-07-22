package com.studentportal.student_course_api.dto;


import java.math.BigDecimal;

public class CourseDTO {
    private String courseCode;
    private String departmentCode; // For linking to Department
    private String courseName;
    private BigDecimal credits;
    private String prerequisites;
    private Boolean isActive;
    private String availableForSemester;
    private String instructor; // Can be instructor ID or name

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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
}