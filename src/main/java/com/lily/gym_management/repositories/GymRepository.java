package com.lily.gym_management.repositories;

import com.lily.gym_management.entities.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymRepository extends JpaRepository<Gym, UUID> {

}
