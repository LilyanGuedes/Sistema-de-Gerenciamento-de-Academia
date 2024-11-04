package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Gym;
import com.lily.gym_management.entities.Instructor;
import com.lily.gym_management.repositories.GymRepository;
import com.lily.gym_management.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterInstructorUseCase {

    private final InstructorRepository instructorRepository;
    private final GymRepository gymRepository;

    @Autowired
    public RegisterInstructorUseCase(InstructorRepository instructorRepository, GymRepository gymRepository) {
        this.instructorRepository = instructorRepository;
        this.gymRepository = gymRepository;
    }

    public Instructor registerInstructor(String name, LocalDate dateOfBirth, String specialization, UUID gymId) {

        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isEmpty()) {
            throw new RuntimeException("Academia não encontrada");
        }

        // Gerar o UUID antes de passar ao construtor
        UUID instructorId = UUID.randomUUID();
        Instructor instructor = new Instructor(instructorId, name, dateOfBirth, specialization, gym.get());

        return instructorRepository.save(instructor);
    }
}
