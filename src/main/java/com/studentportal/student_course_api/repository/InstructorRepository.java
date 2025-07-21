package com.studentportal.student_course_api.repository;

import com.studentportal.student_course_api.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    // Second type parameter (Integer) is the type of the primary key for Instructor (instructor_id is SERIAL/INT)
}
