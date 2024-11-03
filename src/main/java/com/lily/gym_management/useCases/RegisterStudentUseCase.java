package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Student;
import com.lily.gym_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lily.gym_management.entities.Gym;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RegisterStudentUseCase {

    private final StudentRepository studentRepository;

    @Autowired
    public RegisterStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(String name, LocalDate dateOfBirth, Boolean paymentOnTime, Gym gym) {
        UUID studentId = UUID.randomUUID();
        Student student = new Student(studentId, name, dateOfBirth, paymentOnTime, gym);

        return studentRepository.save(student);
    }
}
