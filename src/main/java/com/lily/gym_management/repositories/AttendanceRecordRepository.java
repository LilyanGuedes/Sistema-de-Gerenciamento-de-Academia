package com.lily.gym_management.repositories;

import com.lily.gym_management.entities.AttendanceRecord;
import com.lily.gym_management.entities.Class;
import com.lily.gym_management.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, UUID> {

    //buscar frequência de um aluno por aula
    @Query("SELECT a FROM AttendanceRecord a WHERE a.gymClass = :gymClass AND a.student = :student")
    List<AttendanceRecord> findAttendanceByClassAndStudent(
            @Param("gymClass") Class gymClass,
            @Param("student") Student student
    );

    // buscar todos os registros de frequência em uma aula específica em uma data específica
    @Query("SELECT a FROM AttendanceRecord a WHERE a.gymClass = :gymClass AND a.attendanceDate = :attendanceDate")
    List<AttendanceRecord> findAttendanceByClassAndDate(
            @Param("gymClass") Class gymClass,
            @Param("attendanceDate") LocalDate attendanceDate
    );
}
