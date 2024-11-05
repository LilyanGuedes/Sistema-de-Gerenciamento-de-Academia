package com.lily.gym_management.controllers;

import java.time.LocalDate;
import java.util.UUID;

public class AttendanceRequest {
    private UUID classId;
    private UUID studentId;
    private LocalDate attendanceDate;

    // Getters e Setters
    public UUID getClassId() {
        return classId;
    }

    public void setClassId(UUID classId) {
        this.classId = classId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}
