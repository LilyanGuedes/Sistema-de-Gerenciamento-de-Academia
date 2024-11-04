package com.lily.gym_management.repositories;

import com.lily.gym_management.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<Class, UUID> {
}
