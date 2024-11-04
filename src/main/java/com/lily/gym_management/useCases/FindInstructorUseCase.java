package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Instructor;
import com.lily.gym_management.entities.Student;
import com.lily.gym_management.repositories.InstructorRepository;
import com.lily.gym_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindInstructorUseCase {
    private final InstructorRepository repository;

    @Autowired
    public FindInstructorUseCase(InstructorRepository repository) {
        this.repository = repository;
    }

    public List<Instructor> execute() {
        return repository.findAll();
    }
}
