// src/main/java/com/studentportal/student_course_api/services/DepartmentService.java
package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.repository.DepartmentRepository;
import com.studentportal.student_course_api.dto.DepartmentDTO;
import com.studentportal.student_course_api.mapper.DepartmentMapper; // Import your mapper
import com.studentportal.student_course_api.exception.ResourceNotFoundException; // Import your custom exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper; // Inject your mapper

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        // Convert list of entities to list of DTOs
        return departmentMapper.toDtoList(departments);
    }

    public Optional<DepartmentDTO> getDepartmentByCode(String departmentCode) { // Change return type to Optional<DepartmentDTO>
        return departmentRepository.findByDepartmentCode(departmentCode)
                .map(departmentMapper::toDto); // Map the Optional<Department> to Optional<DepartmentDTO>
        // Removed .orElseThrow() here because the controller now handles the Optional for 404
    }


    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        // Convert DTO to Entity before saving
        Department department = departmentMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        // Convert saved Entity back to DTO for response
        return departmentMapper.toDto(savedDepartment);
    }

/*    public DepartmentDTO updateDepartment(String departmentCode, DepartmentDTO departmentDTO) {
        return departmentRepository.findByDepartmentCode(departmentCode).map(existingDepartment -> {
            // Update fields from DTO to existing entity using the DTO.
            // MapStruct's 'toEntity' method can be used for updates if you pass the existing entity.
            // For simple updates, you can manually set fields or use a dedicated update method in the mapper.
            existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
            existingDepartment.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());
            // No need to handle 'courses' or 'students' collections here directly,
            // as their relationships are managed from their respective entities.

            Department updatedDepartment = departmentRepository.save(existingDepartment);
            return departmentMapper.toDto(updatedDepartment); // Convert updated entity to DTO
        }).orElseThrow(() -> new ResourceNotFoundException("Department not found with code: " + departmentCode));
    }*/

    public Optional<DepartmentDTO> updateDepartment(String departmentCode, DepartmentDTO departmentDTO) {
        // This line is also crucial: it returns Optional<Department> and then applies the update logic within the map
        return departmentRepository.findByDepartmentCode(departmentCode).map(existingDepartment -> {
            existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
            existingDepartment.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());
            Department updatedDepartment = departmentRepository.save(existingDepartment);
            return departmentMapper.toDto(updatedDepartment);
        });
        // Note: We removed the .orElseThrow() here because the controller is now handling the 'not found' case with Optional.orElse()
    }

/*    public void deleteDepartment(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with code: " + departmentCode));
        departmentRepository.delete(department);
    }*/

    public boolean deleteDepartment(String departmentCode) {
        Optional<Department> departmentOptional = departmentRepository.findByDepartmentCode(departmentCode);
        if (departmentOptional.isPresent()) {
            departmentRepository.delete(departmentOptional.get());
            return true; // Return true if deletion was successful
        } else {
            throw new ResourceNotFoundException("Department not found with code: " + departmentCode);
        }
    }
}