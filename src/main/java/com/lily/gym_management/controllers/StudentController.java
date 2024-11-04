package com.lily.gym_management.controllers;
import com.lily.gym_management.entities.Gym;
import com.lily.gym_management.entities.Student;
import com.lily.gym_management.repositories.GymRepository;
import com.lily.gym_management.useCases.FindAllStudentsUseCase;
import com.lily.gym_management.useCases.RegisterStudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final RegisterStudentUseCase registerStudentUseCase;

    private final FindAllStudentsUseCase findAllStudentsUseCase;

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    public StudentController(RegisterStudentUseCase registerStudentUseCase, FindAllStudentsUseCase findAllStudentsUseCase) {
        this.registerStudentUseCase = registerStudentUseCase;
        this.findAllStudentsUseCase = findAllStudentsUseCase;
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody StudentDto studentDto) {
        Gym gym = findGymById(studentDto.gymId);
        if (gym == null) {
            return ResponseEntity.badRequest().build();
        }

        Student student = registerStudentUseCase.registerStudent(studentDto.name(), studentDto.dateOfBirth(), studentDto.paymentOnTime(), gym);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = findAllStudentsUseCase.execute();
        return ResponseEntity.ok(students);
    }

    private Gym findGymById(UUID gymId) {
        return gymRepository.findById(gymId).orElse(null);
    }

    public record StudentDto(
            String name,
            LocalDate dateOfBirth,
            Boolean paymentOnTime,
            UUID gymId
    ) {}
}
