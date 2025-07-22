package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Enrollment;
import com.studentportal.student_course_api.model.Section;
import com.studentportal.student_course_api.model.Student;
import com.studentportal.student_course_api.repository.EnrollmentRepository;
import com.studentportal.student_course_api.repository.SectionRepository;
import com.studentportal.student_course_api.repository.StudentRepository;
import com.studentportal.student_course_api.dto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             SectionRepository sectionRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<EnrollmentDTO> getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id)
                .map(this::convertToDto);
    }

    @Transactional
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        Enrollment enrollment = convertToEntity(enrollmentDTO);
        // Set enrollment date if not provided
        if (enrollment.getEnrollmentDate() == null) {
            enrollment.setEnrollmentDate(LocalDateTime.now());
        }
        return convertToDto(enrollmentRepository.save(enrollment));
    }

    @Transactional
    public Optional<EnrollmentDTO> updateEnrollment(Integer id, EnrollmentDTO enrollmentDTO) {
        return enrollmentRepository.findById(id).map(existingEnrollment -> {
            existingEnrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
            existingEnrollment.setGrade(enrollmentDTO.getGrade());
            existingEnrollment.setStatus(enrollmentDTO.getStatus());

            // Update relationships
            if (enrollmentDTO.getStudentId() != null) {
                studentRepository.findByStudentId(enrollmentDTO.getStudentId())
                        .ifPresent(existingEnrollment::setStudent);
            }
            if (enrollmentDTO.getSectionId() != null) {
                sectionRepository.findById(enrollmentDTO.getSectionId())
                        .ifPresent(existingEnrollment::setSection);
            }
            return convertToDto(enrollmentRepository.save(existingEnrollment));
        });
    }

    @Transactional
    public boolean deleteEnrollment(Integer id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EnrollmentDTO convertToDto(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setEnrollmentId(enrollment.getEnrollmentId());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setGrade(enrollment.getGrade());
        dto.setStatus(enrollment.getStatus());
        if (enrollment.getStudent() != null) {
            dto.setStudentId(enrollment.getStudent().getStudentId());
        }
        if (enrollment.getSection() != null) {
            dto.setSectionId(enrollment.getSection().getSectionId());
        }
        return dto;
    }

    private Enrollment convertToEntity(EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(dto.getEnrollmentId()); // ID might be null for creation
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
        enrollment.setGrade(dto.getGrade());
        enrollment.setStatus(dto.getStatus());
        if (dto.getStudentId() != null) {
            studentRepository.findByStudentId(dto.getStudentId())
                    .ifPresent(enrollment::setStudent);
        }
        if (dto.getSectionId() != null) {
            sectionRepository.findById(dto.getSectionId())
                    .ifPresent(enrollment::setSection);
        }
        return enrollment;
    }
}