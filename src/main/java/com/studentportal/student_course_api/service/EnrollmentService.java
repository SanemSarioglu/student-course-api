package com.studentportal.student_course_api.service;

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

    public Optional<Enrollment> getEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Optional<Enrollment> updateEnrollment(Integer id, Enrollment enrollmentDetails) {
        return enrollmentRepository.findById(id)
                .map(enrollment -> {
                    enrollment.setEnrollmentDate(enrollmentDetails.getEnrollmentDate());
                    enrollment.setGrade(enrollmentDetails.getGrade());
                    enrollment.setStatus(enrollmentDetails.getStatus());
                    // For Student and Section, you would typically fetch these entities
                    // and set them here if the DTO provides studentId and sectionId.
                    return enrollmentRepository.save(enrollment);
                });
    }

    public boolean deleteEnrollment(Integer id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


