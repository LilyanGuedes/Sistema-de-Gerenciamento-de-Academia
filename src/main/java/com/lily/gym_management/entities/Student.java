package com.lily.gym_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;

    public Student() {}

    public Student(UUID id, String name, LocalDate dateOfBirth, Boolean paymentOnTime, Academy academy) {
        super(id, name, dateOfBirth);
        this.paymentOnTime = paymentOnTime;
        this.academy = academy;
    }

    public Boolean getPaymentOnTime() {
        return paymentOnTime;
    }

    public void setPaymentOnTime(Boolean paymentOnTime) {
        this.paymentOnTime = paymentOnTime;
    }

    public String checkPaymentStatus() {
        return paymentOnTime ? "Payment on time" : "Payment pending";
    }

    public Academy getAcademy() {
        return academy; // Getter para a academia
    }

    public void setAcademy(Academy academy) {
        this.academy = academy; // Setter para a academia
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", paymentOnTime=" + paymentOnTime +
                '}';
    }
}