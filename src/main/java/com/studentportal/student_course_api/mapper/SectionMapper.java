package com.studentportal.student_course_api.mapper;

import com.studentportal.student_course_api.dto.SectionDTO;
import com.studentportal.student_course_api.model.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    @Mapping(source = "course.courseCode", target = "courseCode")
    @Mapping(source = "instructor.instructorId", target = "instructorId")
    SectionDTO sectionToSectionDTO(Section section);

    @Mapping(source = "courseCode", target = "course.courseCode")
    @Mapping(source = "instructorId", target = "instructor.instructorId")
    Section sectionDTOToSection(SectionDTO sectionDTO);

    @Mapping(target = "sectionId", ignore = true) // Ignore ID during update
    @Mapping(source = "courseCode", target = "course.courseCode")
    @Mapping(source = "instructorId", target = "instructor.instructorId")
    void updateSectionFromDto(SectionDTO sectionDTO, @MappingTarget Section section);
}
