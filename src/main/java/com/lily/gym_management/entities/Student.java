package com.lily.gym_management.entities;

import jakarta.persistence.Entity;
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

    public Student() {}


    public Student(UUID id, String name, LocalDate dateOfBirth, Boolean paymentOnTime) {
        super(id, name, dateOfBirth);
        this.paymentOnTime = paymentOnTime;
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