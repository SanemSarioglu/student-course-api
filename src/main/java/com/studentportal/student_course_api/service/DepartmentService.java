package com.studentportal.student_course_api.service;

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

    public Optional<Department> getDepartmentByCode(String code) {
        return departmentRepository.findById(code);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> updateDepartment(String code, Department departmentDetails) {
        return departmentRepository.findById(code)
                .map(department -> {
                    department.setDepartmentName(departmentDetails.getDepartmentName());
                    department.setHeadOfDepartment(departmentDetails.getHeadOfDepartment());
                    return departmentRepository.save(department);
                });
    }

    public boolean deleteDepartment(String code) {
        if (departmentRepository.existsById(code)) {
            departmentRepository.deleteById(code);
            return true;
        }
        return false;
    }
}
