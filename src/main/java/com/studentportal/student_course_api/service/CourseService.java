package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Course;
import com.studentportal.student_course_api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByCode(String code) {
        return courseRepository.findById(code);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(String code, Course courseDetails) {
        return courseRepository.findById(code)
                .map(course -> {
                    course.setCourseName(courseDetails.getCourseName());
                    course.setCredits(courseDetails.getCredits());
                    course.setPrerequisites(courseDetails.getPrerequisites());
                    course.setActive(courseDetails.getActive());
                    course.setAvailableForSemester(courseDetails.getAvailableForSemester());
                    course.setInstructor(courseDetails.getInstructor());
                    // For majorDepartment, you would typically fetch the Department entity
                    // and set it here if the DTO provides a majorCode.
                    return courseRepository.save(course);
                });
    }

    public boolean deleteCourse(String code) {
        if (courseRepository.existsById(code)) {
            courseRepository.deleteById(code);
            return true;
        }
        return false;
    }
}

