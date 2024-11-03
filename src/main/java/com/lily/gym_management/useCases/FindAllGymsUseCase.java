package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Gym;
import com.lily.gym_management.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllGymsUseCase {

    private final GymRepository repository;

    @Autowired
    public FindAllGymsUseCase(GymRepository repository) {
        this.repository = repository;
    }

    public List<Gym> execute() {
        return repository.findAll();
    }
}