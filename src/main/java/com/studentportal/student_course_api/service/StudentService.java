package com.studentportal.student_course_api.service;

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

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Integer id, Student studentDetails) {
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
                });
    }

    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


