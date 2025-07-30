package com.studentportal.student_course_api.repository;

import com.studentportal.student_course_api.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    long countBySectionSectionId(Integer sectionId);
}