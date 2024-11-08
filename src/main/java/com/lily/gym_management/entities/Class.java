package com.lily.gym_management.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "class")
public class Class implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "class_id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    private Gym gym;

    @ManyToMany
    @JoinTable(
            name = "class_student",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();


    public Class() {}

    public Class(String name, Instructor instructor, int maxCapacity, Gym gym) {
        this.name = name;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.gym = gym;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Gym getGym() {
        return gym; // Getter para a academia
    }

    public void setGym(Gym gym) {
        this.gym = gym; // Setter para a academia
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student student) {
        if (checkAvailability()) {
            students.add(student);
            System.out.println("Aluno inscrito na aula");
            return true;
        } else {
            System.out.println("A turma está cheia, não é possível matricular o aluno.");
            return false;
        }
    }

    public boolean checkAvailability() {
        return students.size() < maxCapacity;
    }

    public int availableSpots() {
        return maxCapacity - students.size();
    }
}
