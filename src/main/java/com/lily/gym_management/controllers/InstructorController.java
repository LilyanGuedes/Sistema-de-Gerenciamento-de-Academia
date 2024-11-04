package com.lily.gym_management.controllers;

import com.lily.gym_management.entities.Instructor;
import com.lily.gym_management.useCases.FindInstructorUseCase;
import com.lily.gym_management.useCases.RegisterInstructorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final RegisterInstructorUseCase registerInstructorUseCase;
    private final FindInstructorUseCase findInstructorUseCase;

    @Autowired
    public InstructorController(RegisterInstructorUseCase registerInstructorUseCase,
                                FindInstructorUseCase findInstructorUseCase) {
        this.registerInstructorUseCase = registerInstructorUseCase;
        this.findInstructorUseCase = findInstructorUseCase;
    }

    @PostMapping
    public ResponseEntity<Instructor> registerInstructor(@RequestBody InstructorDto instructorDto) {
        try {
            Instructor instructor = registerInstructorUseCase.registerInstructor(
                    instructorDto.name(),
                    instructorDto.dateOfBirth(),
                    instructorDto.specialization(),
                    instructorDto.gymId()
            );
            return ResponseEntity.status(201).body(instructor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> findAllInstructors() {
        List<Instructor> instructors = findInstructorUseCase.execute();
        return ResponseEntity.ok(instructors);
    }

    public record InstructorDto(
            String name,
            LocalDate dateOfBirth,
            String specialization,
            UUID gymId
    ) {}
}
