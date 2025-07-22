package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Department;
import com.studentportal.student_course_api.model.Student;
import com.studentportal.student_course_api.repository.DepartmentRepository;
import com.studentportal.student_course_api.repository.StudentRepository;
import com.studentportal.student_course_api.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(Integer id) {
        return studentRepository.findById(id)
                .map(this::convertToDto);
    }

    public Optional<StudentDTO> getStudentByStudentId(Integer studentId) {
        return studentRepository.findByStudentId(studentId)
                .map(this::convertToDto);
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        return convertToDto(studentRepository.save(student));
    }

    @Transactional
    public Optional<StudentDTO> updateStudent(Integer id, StudentDTO studentDTO) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setStudentId(studentDTO.getStudentId());
            existingStudent.setFirstName(studentDTO.getFirstName());
            existingStudent.setLastName(studentDTO.getLastName());
            existingStudent.setSemester(studentDTO.getSemester());

            // Update department if provided
            if (studentDTO.getDepartmentCode() != null) {
                departmentRepository.findById(studentDTO.getDepartmentCode())
                        .ifPresent(existingStudent::setDepartment);
            }
            return convertToDto(studentRepository.save(existingStudent));
        });
    }

    @Transactional
    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteStudentByStudentId(Integer studentId) {
        Optional<Student> student = studentRepository.findByStudentId(studentId);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }


    private StudentDTO convertToDto(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setSemester(student.getSemester());
        if (student.getDepartment() != null) {
            dto.setDepartmentCode(student.getDepartment().getDepartmentCode());
        }
        return dto;
    }

    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId()); // ID might be null for creation
        student.setStudentId(dto.getStudentId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setSemester(dto.getSemester());
        if (dto.getDepartmentCode() != null) {
            departmentRepository.findById(dto.getDepartmentCode())
                    .ifPresent(student::setDepartment);
        }
        return student;
    }
}