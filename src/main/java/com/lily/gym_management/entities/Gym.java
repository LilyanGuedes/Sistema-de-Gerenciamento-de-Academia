package com.lily.gym_management.entities;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "gym")
public class Gym implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "gym_id")
    private UUID id;


    @Column
    private String name;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students = new HashSet<>();


    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Class> classes = new HashSet<>();


    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AttendanceRecord> attendanceRecords = new HashSet<>();


    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Instructor> instructors = new HashSet<>();

    public Gym() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setGym(this);
    }

    public void addClass(Class gymClass) {
        classes.add(gymClass);
        gymClass.setGym(this); // Definindo a academia na aula
    }

    // Adicionar instrutor
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        instructor.setGym(this); // Definindo a academia no instrutor
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
