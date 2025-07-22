package com.studentportal.student_course_api.dto;

public class DepartmentDTO {

    public DepartmentDTO() {
    }

    public DepartmentDTO(String departmentCode, String departmentName, String headOfDepartment) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.headOfDepartment = headOfDepartment;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    private String departmentCode;
    private String departmentName;
    private String headOfDepartment;
}

