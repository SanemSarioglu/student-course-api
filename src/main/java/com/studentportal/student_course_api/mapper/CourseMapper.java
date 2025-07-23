// src/main/java/com/studentportal/student_course_api/mappers/CourseMapper.java
package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.model.Course;
import com.studentportal.student_course_api.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    // Maps Course entity to CourseDTO
    // If CourseDTO has a simple 'departmentCode' field but Course entity has a 'Department' object,
    // you'd typically handle this by mapping the code or ignoring the object here.
    // For simplicity, assuming CourseDTO has a 'departmentCode' field that matches the entity's department's code.
    @Mapping(source = "department.departmentCode", target = "departmentCode") // Map department object's code to DTO's code
    CourseDTO toDto(Course course);

    // Maps CourseDTO to Course entity
    // We ignore 'sections' and 'department' collections/objects for simplicity in this direct mapping.
    // The 'department' object would typically be fetched and set in the service layer using the departmentCode from DTO.
    //@Mapping(target = "sections", ignore = true)
    //@Mapping(target = "department", ignore = true) // Department object needs to be set manually in service
    @Mapping(target = "majorDepartment", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    List<CourseDTO> toDtoList(List<Course> courses);
    List<Course> toEntityList(List<CourseDTO> courseDTOs);
}
