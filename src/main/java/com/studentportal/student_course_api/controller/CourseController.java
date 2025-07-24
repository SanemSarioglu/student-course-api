package com.studentportal.student_course_api.controller;

import com.studentportal.student_course_api.model.Course;
import com.studentportal.student_course_api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        Course course = courseService.getCourseByCode(code);
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Course> updateCourse(@PathVariable String code, @RequestBody Course courseDetails) {
        Course updatedCourse = courseService.updateCourse(code, courseDetails);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String code) {
        courseService.deleteCourse(code);
        return ResponseEntity.noContent().build();
    }
}