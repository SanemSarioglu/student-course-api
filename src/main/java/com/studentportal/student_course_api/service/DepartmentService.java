package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.repository.DepartmentRepository;
import com.studentportal.student_course_api.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<DepartmentDTO> getDepartmentByCode(String code) {
        return departmentRepository.findById(code)
                .map(this::convertToDto);
    }

    @Transactional
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = convertToEntity(departmentDTO);
        return convertToDto(departmentRepository.save(department));
    }

    @Transactional
    public Optional<DepartmentDTO> updateDepartment(String code, DepartmentDTO departmentDTO) {
        return departmentRepository.findById(code).map(existingDepartment -> {
            existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
            existingDepartment.setHeadOfDepartment(departmentDTO.getHeadOfDepartment());
            return convertToDto(departmentRepository.save(existingDepartment));
        });
    }

    @Transactional
    public boolean deleteDepartment(String code) {
        if (departmentRepository.existsById(code)) {
            departmentRepository.deleteById(code);
            return true;
        }
        return false;
    }

    private DepartmentDTO convertToDto(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setHeadOfDepartment(department.getHeadOfDepartment());
        return dto;
    }

    private Department convertToEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDepartmentName(dto.getDepartmentName());
        department.setHeadOfDepartment(dto.getHeadOfDepartment());
        return department;
    }
}