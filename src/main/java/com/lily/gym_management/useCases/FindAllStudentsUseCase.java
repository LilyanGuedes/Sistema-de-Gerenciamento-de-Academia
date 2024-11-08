package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Student;
import com.lily.gym_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllStudentsUseCase {

    private final StudentRepository repository;

    @Autowired
    public FindAllStudentsUseCase(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> execute() {
        return repository.findAll();
    }
}
