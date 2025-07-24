package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.exception.BadRequestException;
import com.studentportal.student_course_api.exception.ConflictException;
import com.studentportal.student_course_api.exception.ResourceNotFoundException;
import com.studentportal.student_course_api.model.Instructor;
import com.studentportal.student_course_api.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id " + id));
    }

    public Instructor createInstructor(Instructor instructor) {
        if (instructor.getInstructorId() != null && instructorRepository.existsById(instructor.getInstructorId())) {
            throw new ConflictException("Instructor with ID " + instructor.getInstructorId() + " already exists.");
        }
        if (instructor.getDepartment() == null) {
            throw new BadRequestException("Department cannot be null for an instructor.");
        }
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Integer id, Instructor instructorDetails) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructor.setFirstName(instructorDetails.getFirstName());
                    instructor.setLastName(instructorDetails.getLastName());
                    instructor.setEmail(instructorDetails.getEmail());
                    if (instructorDetails.getDepartment() == null) {
                        throw new BadRequestException("Department cannot be null for an instructor.");
                    }
                    instructor.setDepartment(instructorDetails.getDepartment());
                    return instructorRepository.save(instructor);
                }).orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id " + id));
    }

    public void deleteInstructor(Integer id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instructor not found with id " + id);
        }
        instructorRepository.deleteById(id);
    }
}


