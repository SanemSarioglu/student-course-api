package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Course;
import com.studentportal.student_course_api.service.DepartmentService;
import com.studentportal.student_course_api.repository.CourseRepository;
import com.studentportal.student_course_api.repository.DepartmentRepository;
import com.studentportal.student_course_api.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CourseDTO> getCourseByCode(String code) {
        return courseRepository.findById(code)
                .map(this::convertToDto);
    }

    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        return convertToDto(courseRepository.save(course));
    }

    @Transactional
    public Optional<CourseDTO> updateCourse(String code, CourseDTO courseDTO) {
        return courseRepository.findById(code).map(existingCourse -> {
            existingCourse.setCourseName(courseDTO.getCourseName());
            existingCourse.setCredits(courseDTO.getCredits());
            existingCourse.setPrerequisites(courseDTO.getPrerequisites());
            existingCourse.setActive(courseDTO.getActive());
            existingCourse.setAvailableForSemester(courseDTO.getAvailableForSemester());
            existingCourse.setInstructor(courseDTO.getInstructor());

            // Update department if provided
            if (courseDTO.getDepartmentCode() != null) {
                departmentRepository.findById(courseDTO.getDepartmentCode())
                        .ifPresent(existingCourse::setDepartment);
            }
            return convertToDto(courseRepository.save(existingCourse));
        });
    }

    @Transactional
    public boolean deleteCourse(String code) {
        if (courseRepository.existsById(code)) {
            courseRepository.deleteById(code);
            return true;
        }
        return false;
    }

    private CourseDTO convertToDto(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setCourseCode(course.getCourseCode());
        dto.setCourseName(course.getCourseName());
        dto.setCredits(course.getCredits());
        dto.setPrerequisites(course.getPrerequisites());
        dto.setActive(course.getActive());
        dto.setAvailableForSemester(course.getAvailableForSemester());
        dto.setInstructor(course.getInstructor());
        if (course.getDepartment() != null) {
            dto.setDepartmentCode(course.getDepartment().getDepartmentCode());
        }
        return dto;
    }

    private Course convertToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());
        course.setPrerequisites(dto.getPrerequisites());
        course.setActive(dto.getActive());
        course.setAvailableForSemester(dto.getAvailableForSemester());
        course.setInstructor(dto.getInstructor());
        if (dto.getDepartmentCode() != null) {
            departmentRepository.findById(dto.getDepartmentCode())
                    .ifPresent(course::setDepartment);
        }
        return course;
    }
}