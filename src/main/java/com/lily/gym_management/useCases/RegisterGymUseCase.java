package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Gym;
import com.lily.gym_management.repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterGymUseCase {

    private final GymRepository repository;

    @Autowired
    public RegisterGymUseCase(GymRepository repository) {
        this.repository = repository;
    }

    public Gym execute(Gym gym) {
        if (gym.getId() == null) {
            gym.setId(UUID.randomUUID());
        }
        return repository.save(gym);
    }
}