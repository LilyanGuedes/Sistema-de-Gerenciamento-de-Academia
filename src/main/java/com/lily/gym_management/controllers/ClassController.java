package com.lily.gym_management.controllers;

import com.lily.gym_management.entities.Class;
import com.lily.gym_management.entities.Student;
import com.lily.gym_management.useCases.EnrollStudentInClassUseCase;
import com.lily.gym_management.useCases.FindAllClassesUseCase;
import com.lily.gym_management.useCases.RegisterClassUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final RegisterClassUseCase registerClassUseCase;

    private final FindAllClassesUseCase findAllClassesUseCase;

    private final EnrollStudentInClassUseCase enrollStudentInClassUseCase;

    @Autowired
    public ClassController(RegisterClassUseCase registerClassUseCase, FindAllClassesUseCase findAllClassesUseCase, EnrollStudentInClassUseCase enrollStudentInClassUseCase) {
        this.registerClassUseCase = registerClassUseCase;
        this.findAllClassesUseCase = findAllClassesUseCase;
        this.enrollStudentInClassUseCase = enrollStudentInClassUseCase;
    }

    @PostMapping
    public ResponseEntity<Class> registerClass(@RequestBody ClassDto classDto) {
        try {
            Class aula = registerClassUseCase.registerClass(
                    classDto.name(),
                    classDto.instructorId(),
                    classDto.gymId(),
                    classDto.maxCapacity()
            );
            return ResponseEntity.status(201).body(aula);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Class>> findAllClasses() {
        List<Class> classes = findAllClassesUseCase.execute();
        return ResponseEntity.ok(classes);
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enrollStudentInClass(@RequestBody EnrollStudentDto enrollStudentDto) {
        try {
            boolean enrolled = enrollStudentInClassUseCase.enrollStudent(
                    enrollStudentDto.classId(),
                    enrollStudentDto.studentId()
            );
            if (enrolled) {
                return ResponseEntity.ok("Aluno inscrito com sucesso na aula.");
            } else {
                return ResponseEntity.badRequest().body("Falha ao inscrever o aluno.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public record ClassDto(
            String name,
            UUID instructorId,
            UUID gymId,
            int maxCapacity
    ) {}

    public record EnrollStudentDto(
            UUID classId,
            UUID studentId
    ) {}

}
