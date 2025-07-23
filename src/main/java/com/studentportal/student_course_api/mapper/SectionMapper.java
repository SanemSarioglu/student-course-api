// src/main/java/com/studentportal/student_course_api/mappers/SectionMapper.java
package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.model.Section;
import com.studentportal.student_course_api.dto.SectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    // Maps Section entity to SectionDTO
    @Mapping(source = "course.courseCode", target = "courseCode") // Map course object's code to DTO's code
    @Mapping(source = "instructor.instructorId", target = "instructorId") // Map instructor object's ID to DTO's ID
    SectionDTO toDto(Section section);

    // Maps SectionDTO to Section entity
    // Ignore 'enrollments', 'course', and 'instructor' collections/objects.
    //@Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "course", ignore = true) // Course object needs to be set manually in service
    @Mapping(target = "instructor", ignore = true) // Instructor object needs to be set manually in service
    Section toEntity(SectionDTO sectionDTO);

    List<SectionDTO> toDtoList(List<Section> sections);
    List<Section> toEntityList(List<SectionDTO> sectionDTOs);
}
