package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.dto.DepartmentDTO;
import com.studentportal.student_course_api.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO departmentToDepartmentDTO(Department department);

    Department departmentDTOToDepartment(DepartmentDTO departmentDTO);

    void updateDepartmentFromDto(DepartmentDTO departmentDTO, @MappingTarget Department department);
}
