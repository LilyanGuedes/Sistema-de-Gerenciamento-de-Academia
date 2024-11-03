package com.lily.gym_management.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class")
public class Class implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "max_capacity", nullable = false)
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToMany
    @JoinTable(
            name = "class_student",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();


    public Class() {}

    public Class(String name, Instructor instructor, int maxCapacity) {
        this.name = name;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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