package com.studentportal.student_course_api.repository;

import com.studentportal.student_course_api.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Marks this as a Spring Data repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    // JpaRepository provides methods like findAll(), findById(), save(), delete()
    // The second type parameter (String) is the type of the primary key for Department (department_code is VARCHAR)

    Optional<Department> findByDepartmentCode(String departmentCode);
}
