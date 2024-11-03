package com.lily.gym_management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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

    public Instructor() {}

    public Instructor(UUID id, String name, LocalDate dateOfBirth, String specialization) {
        super(id, name, dateOfBirth);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



}
