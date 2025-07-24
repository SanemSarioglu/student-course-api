package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.exception.ConflictException;
import com.studentportal.student_course_api.exception.ResourceNotFoundException;
import com.studentportal.student_course_api.model.Section;
import com.studentportal.student_course_api.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Integer id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id " + id));
    }

    public Section createSection(Section section) {
        if (section.getSectionId() != null && sectionRepository.existsById(section.getSectionId())) {
            throw new ConflictException("Section with ID " + section.getSectionId() + " already exists.");
        }
        return sectionRepository.save(section);
    }

    public Section updateSection(Integer id, Section sectionDetails) {
        return sectionRepository.findById(id)
                .map(section -> {
                    section.setSemester(sectionDetails.getSemester());
                    section.setYear(sectionDetails.getYear());
                    section.setCapacity(sectionDetails.getCapacity());
                    section.setCurrentEnrollment(sectionDetails.getCurrentEnrollment());
                    section.setStatus(sectionDetails.getStatus());
                    section.setSectionNumber(sectionDetails.getSectionNumber());
                    // For Course and Instructor, you would typically fetch these entities
                    // and set them here if the DTO provides courseCode and instructorId.
                    return sectionRepository.save(section);
                }).orElseThrow(() -> new ResourceNotFoundException("Section not found with id " + id));
    }

    public void deleteSection(Integer id) {
        if (!sectionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Section not found with id " + id);
        }
        sectionRepository.deleteById(id);
    }
}

