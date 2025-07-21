package com.studentportal.student_course_api.service;

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

    public Optional<Instructor> getInstructorById(Integer id) {
        return instructorRepository.findById(id);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Optional<Instructor> updateInstructor(Integer id, Instructor instructorDetails) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructor.setFirstName(instructorDetails.getFirstName());
                    instructor.setLastName(instructorDetails.getLastName());
                    instructor.setEmail(instructorDetails.getEmail());
                    return instructorRepository.save(instructor);
                });
    }

    public boolean deleteInstructor(Integer id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

