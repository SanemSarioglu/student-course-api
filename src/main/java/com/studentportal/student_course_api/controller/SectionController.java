package com.studentportal.student_course_api.controller;

import com.studentportal.student_course_api.model.Section;
import com.studentportal.student_course_api.service.SectionService; // Use the service layer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sections")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable Integer id) {
        Section section = sectionService.getSectionById(id);
        return ResponseEntity.ok(section);
    }

    @PostMapping
    public Section createSection(@RequestBody Section section) {
        return sectionService.createSection(section);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable Integer id, @RequestBody Section sectionDetails) {
        Section updatedSection = sectionService.updateSection(id, sectionDetails);
        return ResponseEntity.ok(updatedSection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}