package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.dto.StudentDTO;
import com.studentportal.student_course_api.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "department.departmentCode", target = "departmentCode")
    StudentDTO studentToStudentDTO(Student student);

    @Mapping(source = "departmentCode", target = "department.departmentCode")
    Student studentDTOToStudent(StudentDTO studentDTO);

    @Mapping(target = "id", ignore = true) // Ignore ID during update
    @Mapping(source = "departmentCode", target = "department.departmentCode")
    void updateStudentFromDto(StudentDTO studentDTO, @MappingTarget Student student);
}
