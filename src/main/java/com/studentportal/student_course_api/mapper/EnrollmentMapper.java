// src/main/java/com/studentportal/student_course_api/mappers/EnrollmentMapper.java
package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.model.Enrollment;
import com.studentportal.student_course_api.dto.EnrollmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    // Maps Enrollment entity to EnrollmentDTO
    @Mapping(source = "student.studentId", target = "studentId") // Map student object's ID to DTO's ID
    @Mapping(source = "section.sectionId", target = "sectionId") // Map section object's ID to DTO's ID
    EnrollmentDTO toDto(Enrollment enrollment);

    // Maps EnrollmentDTO to Enrollment entity
    // Ignore 'student' and 'section' objects.
    @Mapping(target = "student", ignore = true) // Student object needs to be set manually in service
    @Mapping(target = "section", ignore = true) // Section object needs to be set manually in service
    Enrollment toEntity(EnrollmentDTO enrollmentDTO);

    List<EnrollmentDTO> toDtoList(List<Enrollment> enrollments);
    List<Enrollment> toEntityList(List<EnrollmentDTO> enrollmentDTOs);
}
