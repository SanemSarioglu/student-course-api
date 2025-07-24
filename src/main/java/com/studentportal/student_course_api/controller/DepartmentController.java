package com.studentportal.student_course_api.controller;

import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.service.DepartmentService; // Service sınıfını kullanmak için
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService; // Repository yerine Service enjekte edilir

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Department> getDepartmentByCode(@PathVariable String code) {
        Department department = departmentService.getDepartmentByCode(code);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String code, @RequestBody Department departmentDetails) {
        Department updatedDepartment = departmentService.updateDepartment(code, departmentDetails);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String code) {
        departmentService.deleteDepartment(code);
        return ResponseEntity.noContent().build();
    }
}
