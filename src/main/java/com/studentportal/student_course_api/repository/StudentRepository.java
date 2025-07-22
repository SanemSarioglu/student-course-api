package com.studentportal.student_course_api.repository;

import com.studentportal.student_course_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentId(Integer studentId);
    void deleteByStudentId(Integer studentId);
}
