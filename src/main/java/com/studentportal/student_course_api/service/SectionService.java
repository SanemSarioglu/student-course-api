package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Course;
import com.studentportal.student_course_api.model.Instructor;
import com.studentportal.student_course_api.model.Section;
import com.studentportal.student_course_api.repository.CourseRepository;
import com.studentportal.student_course_api.repository.InstructorRepository;
import com.studentportal.student_course_api.repository.SectionRepository;
import com.studentportal.student_course_api.dto.SectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository,
                          CourseRepository courseRepository,
                          InstructorRepository instructorRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<SectionDTO> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<SectionDTO> getSectionById(Integer id) {
        return sectionRepository.findById(id)
                .map(this::convertToDto);
    }

    @Transactional
    public SectionDTO createSection(SectionDTO sectionDTO) {
        Section section = convertToEntity(sectionDTO);
        return convertToDto(sectionRepository.save(section));
    }

    @Transactional
    public Optional<SectionDTO> updateSection(Integer id, SectionDTO sectionDTO) {
        return sectionRepository.findById(id).map(existingSection -> {
            existingSection.setYear(sectionDTO.getYear());
            existingSection.setCapacity(sectionDTO.getCapacity());
            existingSection.setCurrentEnrollment(sectionDTO.getCurrentEnrollment());
            existingSection.setStatus(sectionDTO.getStatus());

            // Update relationships
            if (sectionDTO.getCourseCode() != null) {
                courseRepository.findById(sectionDTO.getCourseCode())
                        .ifPresent(existingSection::setCourse);
            }
            if (sectionDTO.getInstructorId() != null) {
                instructorRepository.findById(sectionDTO.getInstructorId())
                        .ifPresent(existingSection::setInstructor);
            }
            return convertToDto(sectionRepository.save(existingSection));
        });
    }

    @Transactional
    public boolean deleteSection(Integer id) {
        if (sectionRepository.existsById(id)) {
            sectionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SectionDTO convertToDto(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setSectionId(section.getSectionId());
        dto.setYear(section.getYear());
        dto.setCapacity(section.getCapacity());
        dto.setCurrentEnrollment(section.getCurrentEnrollment());
        dto.setStatus(section.getStatus());
        if (section.getCourse() != null) {
            dto.setCourseCode(section.getCourse().getCourseCode());
        }
        if (section.getInstructor() != null) {
            dto.setInstructorId(section.getInstructor().getInstructorId());
        }
        return dto;
    }

    private Section convertToEntity(SectionDTO dto) {
        Section section = new Section();
        section.setSectionId(dto.getSectionId()); // ID might be null for creation
        section.setYear(dto.getYear());
        section.setCapacity(dto.getCapacity());
        section.setCurrentEnrollment(dto.getCurrentEnrollment());
        section.setStatus(dto.getStatus());
        if (dto.getCourseCode() != null) {
            courseRepository.findById(dto.getCourseCode())
                    .ifPresent(section::setCourse);
        }
        if (dto.getInstructorId() != null) {
            instructorRepository.findById(dto.getInstructorId())
                    .ifPresent(section::setInstructor);
        }
        return section;
    }
}