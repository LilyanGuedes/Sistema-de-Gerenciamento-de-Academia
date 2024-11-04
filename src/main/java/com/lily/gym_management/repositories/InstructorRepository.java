package com.lily.gym_management.repositories;

import com.lily.gym_management.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, UUID> {
}
