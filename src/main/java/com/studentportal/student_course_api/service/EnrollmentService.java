package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.exception.ConflictException;
import com.studentportal.student_course_api.exception.ResourceNotFoundException;
import com.studentportal.student_course_api.model.Enrollment;
import com.studentportal.student_course_api.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id " + id));
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        if (enrollment.getEnrollmentId() != null && enrollmentRepository.existsById(enrollment.getEnrollmentId())) {
            throw new ConflictException("Enrollment with ID " + enrollment.getEnrollmentId() + " already exists.");
        }
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(Integer id, Enrollment enrollmentDetails) {
        return enrollmentRepository.findById(id)
                .map(enrollment -> {
                    enrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());
                    enrollment.setGrade(enrollmentDetails.getGrade());
                    enrollment.setStatus(enrollmentDetails.getStatus());
                    // For Student and Section, you would typically fetch these entities
                    // and set them here if the DTO provides studentId and sectionId.
                    return enrollmentRepository.save(enrollment);
                }).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id " + id));
    }

    public void deleteEnrollment(Integer id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found with id " + id);
        }
        enrollmentRepository.deleteById(id);
    }
}


