package com.studentportal.student_course_api.controller;

import com.studentportal.student_course_api.model.Instructor;
import com.studentportal.student_course_api.service.InstructorService; // Use the service layer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
        Instructor instructor = instructorService.getInstructorById(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorService.createInstructor(instructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Integer id, @RequestBody Instructor instructorDetails) {
        Instructor updatedInstructor = instructorService.updateInstructor(id, instructorDetails);
        return ResponseEntity.ok(updatedInstructor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}