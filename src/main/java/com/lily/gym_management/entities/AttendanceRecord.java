package com.lily.gym_management.entities;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "attendance_record")
public class AttendanceRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class gymClass;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;


    public AttendanceRecord() {}

    public AttendanceRecord(Class gymClass, Student student, LocalDate attendanceDate, Gym gym) {
        this.gymClass = gymClass;
        this.student = student;
        this.attendanceDate = attendanceDate;
        this.gym = gym;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Class getGymClass() {
        return gymClass;
    }

    public void setGymClass(Class gymClass) {
        this.gymClass = gymClass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "id=" + id +
                ", gymClass=" + gymClass.getName() +
                ", student=" + student.getName() +
                ", attendanceDate=" + attendanceDate +
                '}';
    }
}
