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
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;
    public Instructor() {}

    public Instructor(UUID id, String name, LocalDate dateOfBirth, String specialization, Academy academy) {
        super(id, name, dateOfBirth);
        this.specialization = specialization;
        this.academy = academy;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public Academy getAcademy() {
        return academy; // Getter para a academia
    }

    public void setAcademy(Academy academy) {
        this.academy = academy; // Setter para a academia
    }


}
