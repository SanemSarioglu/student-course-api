package com.studentportal.student_course_api.controller;

import com.studentportal.student_course_api.dto.DepartmentDTO;
import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.service.DepartmentService; // Service sınıfını kullanmak için
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService; // Repository yerine Service enjekte edilir

    /*@GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }*/

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDTO> getDepartmentByCode(@PathVariable String code) {
        return departmentService.getDepartmentByCode(code)
                .map(departmentDTO -> ResponseEntity.ok(departmentDTO))
                .orElse(ResponseEntity.notFound().build());
    }


    /*    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }*/


    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) { // FIX 5: Change parameter to DepartmentDTO, change return type to ResponseEntity<DepartmentDTO>
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        // It's good practice to return 201 Created for POST requests
        return ResponseEntity.status(201).body(createdDepartment);
    }


/*    @PutMapping("/{code}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String code, @RequestBody Department departmentDetails) {
        return departmentService.updateDepartment(code, departmentDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    @PutMapping("/{code}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable String code, @RequestBody DepartmentDTO departmentDetailsDTO) { // FIX 6: Change parameter to DepartmentDetailsDTO, change return type to ResponseEntity<DepartmentDTO>
        return departmentService.updateDepartment(code, departmentDetailsDTO)
                .map(updatedDepartment -> ResponseEntity.ok(updatedDepartment)) // FIX 7: Explicitly provide type for ResponseEntity.ok()
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String code) {
        if (departmentService.deleteDepartment(code)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
