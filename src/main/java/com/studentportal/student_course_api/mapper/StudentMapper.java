// src/main/java/com/studentportal/student_course_api/mappers/StudentMapper.java
package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.model.Student;
import com.studentportal.student_course_api.dto.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Maps Student entity to StudentDTO
    @Mapping(source = "department.departmentCode", target = "departmentCode") // Map department object's code to DTO's code
    StudentDTO toDto(Student student);

    // Maps StudentDTO to Student entity
    //@Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "department", ignore = true) // Department object needs to be set manually in service
    Student toEntity(StudentDTO studentDTO);

    List<StudentDTO> toDtoList(List<Student> students);
    List<Student> toEntityList(List<StudentDTO> studentDTOs);
}
