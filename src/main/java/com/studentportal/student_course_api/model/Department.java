// src/main/java/com/studentportal/student_course_api/model/Department.java
package com.studentportal.student_course_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "department_code", length = 20)
    private String departmentCode;

    @Column(name = "department_name", nullable = false, unique = true, length = 100)
    private String departmentName;

    @Column(name = "head_of_department", length = 100)
    private String headOfDepartment;

    // Default constructor is required by JPA
    public Department() {
    }

    // You can add a constructor for convenience, but not strictly required by JPA if a default one exists
    public Department(String departmentCode, String departmentName, String headOfDepartment) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.headOfDepartment = headOfDepartment;
    }

    // Getters
    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    // Setters
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }
}