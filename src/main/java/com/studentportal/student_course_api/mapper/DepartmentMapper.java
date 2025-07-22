// src/main/java/com/studentportal/student_course_api/mappers/DepartmentMapper.java
package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.dto.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring") // Makes it a Spring component for injection
public interface DepartmentMapper {

    // Maps Department entity to DepartmentDTO
    DepartmentDTO toDto(Department department);

    // Maps DepartmentDTO to Department entity
    // We ignore 'courses' and 'students' collections here because they are complex relationships
    // that are usually not mapped directly in a simple DTO conversion to avoid circular references
    // or to simplify the DTO. You would handle these relationships in your service logic.
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "students", ignore = true)
    Department toEntity(DepartmentDTO departmentDTO);

    // Maps a list of Department entities to a list of DepartmentDTOs
    List<DepartmentDTO> toDtoList(List<Department> departments);

    // Maps a list of DepartmentDTOs to a list of Department entities
    List<Department> toEntityList(List<DepartmentDTO> departmentDTOs);
}