// src/main/java/com/studentportal/student_course_api/mappers/InstructorMapper.java
package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.model.Instructor;
import com.studentportal.student_course_api.dto.InstructorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    // Maps Instructor entity to InstructorDTO
   //@Mapping(source = "departmentCode", target = "departmentCode") // Map department object's code to DTO's code
    InstructorDTO toDto(Instructor instructor);

    // Maps InstructorDTO to Instructor entity
    // Ignore 'courses' and 'department' collections/objects.
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "department", ignore = true) // Department object needs to be set manually in service
    Instructor toEntity(InstructorDTO instructorDTO);

    List<InstructorDTO> toDtoList(List<Instructor> instructors);
    List<Instructor> toEntityList(List<InstructorDTO> instructorDTOs);
}
