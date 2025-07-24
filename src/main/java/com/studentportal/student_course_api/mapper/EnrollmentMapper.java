package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.dto.EnrollmentDTO;
import com.studentportal.student_course_api.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "section.sectionId", target = "sectionId")
    EnrollmentDTO enrollmentToEnrollmentDTO(Enrollment enrollment);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "sectionId", target = "section.sectionId")
    Enrollment enrollmentDTOToEnrollment(EnrollmentDTO enrollmentDTO);

    @Mapping(target = "enrollmentId", ignore = true) // Ignore ID during update
    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "sectionId", target = "section.sectionId")
    void updateEnrollmentFromDto(EnrollmentDTO enrollmentDTO, @MappingTarget Enrollment enrollment);
}
