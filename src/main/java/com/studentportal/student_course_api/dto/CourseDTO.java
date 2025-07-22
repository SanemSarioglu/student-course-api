package com.studentportal.student_course_api.dto;

public class CourseDTO {
    private String courseCode;
    private String courseName;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
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

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
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

    private Double credits;
    private String prerequisites;
    private Boolean isActive;
    private String majorCode; // Only the foreign key, not the full Department object
    private String availableForSemester;
    private String instructor;
}
