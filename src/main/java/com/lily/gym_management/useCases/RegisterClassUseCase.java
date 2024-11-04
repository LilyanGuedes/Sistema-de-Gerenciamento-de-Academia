package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Class;
import com.lily.gym_management.entities.Gym;
import com.lily.gym_management.entities.Instructor;
import com.lily.gym_management.repositories.ClassRepository;
import com.lily.gym_management.repositories.GymRepository;
import com.lily.gym_management.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegisterClassUseCase {

    private final ClassRepository classRepository;
    private final InstructorRepository instructorRepository;
    private final GymRepository gymRepository;


    @Autowired
    public RegisterClassUseCase(
            ClassRepository classRepository,
            InstructorRepository instructorRepository,
            GymRepository gymRepository) {
        this.classRepository = classRepository;
        this.instructorRepository = instructorRepository;
        this.gymRepository = gymRepository;
    }


    public Class registerClass(String name, UUID instructorId, UUID gymId, int maxCapacity) {
        Optional<Instructor> instructor = instructorRepository.findById(instructorId);
        if (instructor.isEmpty()) {
            throw new RuntimeException("Instrutor não encontrado");
        }

        Optional<Gym> gym = gymRepository.findById(gymId);
        if (gym.isEmpty()) {
            throw new RuntimeException("Academia não encontrada");
        }

        Class aula = new Class(name, instructor.get(), maxCapacity, gym.get());
        aula.setId(UUID.randomUUID());
        return classRepository.save(aula);
    }

}
