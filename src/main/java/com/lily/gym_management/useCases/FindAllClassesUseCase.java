package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Class;
import com.lily.gym_management.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllClassesUseCase {

    private final ClassRepository repository;

    @Autowired
    public FindAllClassesUseCase(ClassRepository repository) {
        this.repository = repository;
    }

    public List<Class> execute() {
        return repository.findAll();
    }
}
