package com.studentportal.student_course_api.service;

import com.studentportal.student_course_api.model.Instructor;
import com.studentportal.student_course_api.repository.InstructorRepository;
import com.studentportal.student_course_api.dto.InstructorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<InstructorDTO> getInstructorById(Integer id) {
        return instructorRepository.findById(id)
                .map(this::convertToDto);
    }

    @Transactional
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = convertToEntity(instructorDTO);
        return convertToDto(instructorRepository.save(instructor));
    }

    @Transactional
    public Optional<InstructorDTO> updateInstructor(Integer id, InstructorDTO instructorDTO) {
        return instructorRepository.findById(id).map(existingInstructor -> {
            existingInstructor.setFirstName(instructorDTO.getFirstName());
            existingInstructor.setLastName(instructorDTO.getLastName());
            existingInstructor.setEmail(instructorDTO.getEmail());
            return convertToDto(instructorRepository.save(existingInstructor));
        });
    }

    @Transactional
    public boolean deleteInstructor(Integer id) {
        if (instructorRepository.existsById(id)) {
            instructorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private InstructorDTO convertToDto(Instructor instructor) {
        InstructorDTO dto = new InstructorDTO();
        dto.setInstructorId(instructor.getInstructorId());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        dto.setEmail(instructor.getEmail());
        return dto;
    }

    private Instructor convertToEntity(InstructorDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(dto.getInstructorId()); // ID might be null for creation
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        instructor.setEmail(dto.getEmail());
        return instructor;
    }
}