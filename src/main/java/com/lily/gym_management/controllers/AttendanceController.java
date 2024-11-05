package com.lily.gym_management.controllers;

import com.lily.gym_management.entities.AttendanceRecord;
import com.lily.gym_management.useCases.AttendanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceUseCase attendanceUseCase;

    @Autowired
    public AttendanceController(AttendanceUseCase attendanceUseCase) {
        this.attendanceUseCase = attendanceUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<AttendanceRecord> registerAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        AttendanceRecord attendanceRecord = attendanceUseCase.registerAttendance(
                attendanceRequest.getClassId(),
                attendanceRequest.getStudentId(),
                attendanceRequest.getAttendanceDate()
        );
        return new ResponseEntity<>(attendanceRecord, HttpStatus.CREATED);
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceRecordsByClass(@PathVariable UUID classId) {
        List<AttendanceRecord> records = attendanceUseCase.getAttendanceRecordsByClass(classId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceRecordsByStudent(@PathVariable UUID studentId) {
        List<AttendanceRecord> records = attendanceUseCase.getAttendanceRecordsByStudent(studentId);
        return ResponseEntity.ok(records);
    }
}
