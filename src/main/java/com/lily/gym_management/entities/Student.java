package com.lily.gym_management.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student extends Member implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Boolean paymentOnTime;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    public Student() {}

    public Student(UUID id, String name, LocalDate dateOfBirth, Boolean paymentOnTime, Gym gym) {
        super(id, name, dateOfBirth);
        this.paymentOnTime = paymentOnTime;
        this.gym = gym;
    }

    public Boolean getPaymentOnTime() {
        return paymentOnTime;
    }

    public void setPaymentOnTime(Boolean paymentOnTime) {
        this.paymentOnTime = paymentOnTime;
    }

    // Método para verificar se o pagamento está em dia
    public boolean isPaymentUpToDate() {
        return Boolean.TRUE.equals(paymentOnTime);
    }

    public Gym getGym() {
        return gym; // Getter para a academia
    }

    public void setGym(Gym gym) {
        this.gym = gym; // Setter para a academia
    }


}