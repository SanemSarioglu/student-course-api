package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.exception.ConflictException;
import com.studentportal.student_course_api.exception.ResourceNotFoundException;
import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bu sınıfın bir servis bileşeni olduğunu belirtir
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentByCode(String code) {
        return departmentRepository.findById(code)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with code " + code));
    }

    public Department createDepartment(Department department) {
        if (departmentRepository.existsById(department.getDepartmentCode())) {
            throw new ConflictException("Department with code " + department.getDepartmentCode() + " already exists.");
        }
        return departmentRepository.save(department);
    }

    public Department updateDepartment(String code, Department departmentDetails) {
        return departmentRepository.findById(code)
                .map(department -> {
                    department.setDepartmentName(departmentDetails.getDepartmentName());
                    department.setHeadOfDepartment(departmentDetails.getHeadOfDepartment());
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with code " + code));
    }

    public void deleteDepartment(String code) {
        if (!departmentRepository.existsById(code)) {
            throw new ResourceNotFoundException("Department not found with code " + code);
        }
        departmentRepository.deleteById(code);
    }
}

