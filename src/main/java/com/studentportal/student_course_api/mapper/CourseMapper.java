package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.dto.CourseDTO;
import com.studentportal.student_course_api.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "majorDepartment.departmentCode", target = "majorCode")
    CourseDTO courseToCourseDTO(Course course);

    @Mapping(source = "majorCode", target = "majorDepartment.departmentCode")
    Course courseDTOToCourse(CourseDTO courseDTO);

    @Mapping(target = "courseCode", ignore = true) // Ignore ID during update
    @Mapping(source = "majorCode", target = "majorDepartment.departmentCode")
    void updateCourseFromDto(CourseDTO courseDTO, @MappingTarget Course course);
}
