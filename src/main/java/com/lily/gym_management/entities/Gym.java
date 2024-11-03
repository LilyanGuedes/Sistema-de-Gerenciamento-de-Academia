package com.lily.gym_management.entities;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gym")
public class Gym implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Lista de alunos
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students = new HashSet<>();

    // Lista de aulas
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Class> classes = new HashSet<>();

    // Lista de registros de frequência
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AttendanceRecord> attendanceRecords = new HashSet<>();

    // Relação um-para-muitos com instrutores
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Instructor> instructors = new HashSet<>();

    public Gym() {}

    // Adicionar aluno
    public void addStudent(Student student) {
        students.add(student);
        student.setGym(this); // Definindo a academia no aluno
    }

    // Adicionar aula
    public void addClass(Class gymClass) {
        classes.add(gymClass);
        gymClass.setGym(this); // Definindo a academia na aula
    }

    // Inscrever aluno em aula
    public boolean enrollStudentInClass(Student student, Class gymClass) {
        return gymClass.enrollStudent(student);
    }

    // Registrar frequência
    public void registerAttendance(Class gymClass, Student student, LocalDate attendanceDate, Gym gym) {
        AttendanceRecord record = new AttendanceRecord(gymClass, student, attendanceDate, gym);
        attendanceRecords.add(record);
    }

    // Listar registros de frequência
    public Set<AttendanceRecord> listAttendanceRecords() {
        return attendanceRecords;
    }

    // Adicionar instrutor
    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        instructor.setGym(this); // Definindo a academia no instrutor
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Class> getClasses() {
        return classes;
    }

    public void setClasses(Set<Class> classes) {
        this.classes = classes;
    }

    public Set<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void setAttendanceRecords(Set<AttendanceRecord> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", students=" + students +
                ", classes=" + classes +
                ", attendanceRecords=" + attendanceRecords +
                ", instructors=" + instructors +
                '}';
    }
}
