package com.lily.gym_management.repositories;

import com.lily.gym_management.entities.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AcademyRepository extends JpaRepository<Academy, UUID> {

}
