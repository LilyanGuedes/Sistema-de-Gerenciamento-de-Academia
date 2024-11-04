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
    List<AttendanceRecord> findByGymClass(Class gymClass);

    List<AttendanceRecord> findByStudent(Student student);
}
