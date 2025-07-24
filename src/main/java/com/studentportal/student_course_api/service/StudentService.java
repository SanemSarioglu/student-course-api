package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.exception.ConflictException;
import com.studentportal.student_course_api.exception.ResourceNotFoundException;
import com.studentportal.student_course_api.model.Student;
import com.studentportal.student_course_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public Student createStudent(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new ConflictException("Student with ID " + student.getId() + " already exists.");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student studentDetails) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(studentDetails.getFirstName());
                    student.setLastName(studentDetails.getLastName());
                    student.setSemester(studentDetails.getSemester());
                    // For department, you would typically fetch the Department entity
                    // and set it here if the DTO provides a departmentCode.
                    // Example:
                    // if (studentDetails.getDepartment() != null && studentDetails.getDepartment().getDepartmentCode() != null) {
                    //    departmentRepository.findById(studentDetails.getDepartment().getDepartmentCode()).ifPresent(student::setDepartment);
                    // }
                    return studentRepository.save(student);
                }).orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}


