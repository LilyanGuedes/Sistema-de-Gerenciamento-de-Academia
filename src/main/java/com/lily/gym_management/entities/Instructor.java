package com.lily.gym_management.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "instructor")
public class Instructor extends Member implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;
    public Instructor() {}

    public Instructor(UUID id, String name, LocalDate dateOfBirth, String specialization, Gym gym) {
        super(id, name, dateOfBirth);
        this.specialization = specialization;
        this.gym = gym;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public Gym getGym() {
        return gym; // Getter para a academia
    }

    public void setGym(Gym gym) {
        this.gym = gym; // Setter para a academia
    }


}
