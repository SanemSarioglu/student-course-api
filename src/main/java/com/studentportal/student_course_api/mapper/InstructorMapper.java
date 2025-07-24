package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.dto.InstructorDTO;
import com.studentportal.student_course_api.model.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);

    InstructorDTO instructorToInstructorDTO(Instructor instructor);

    Instructor instructorDTOToInstructor(InstructorDTO instructorDTO);

    void updateInstructorFromDto(InstructorDTO instructorDTO, @MappingTarget Instructor instructor);
}
