package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.AttendanceRecord;
import com.lily.gym_management.entities.Class;
import com.lily.gym_management.entities.Student;
import com.lily.gym_management.repositories.AttendanceRecordRepository;
import com.lily.gym_management.repositories.ClassRepository;
import com.lily.gym_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendanceUseCase {

    private final AttendanceRecordRepository attendanceRecordRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceUseCase(
            AttendanceRecordRepository attendanceRecordRepository,
            ClassRepository classRepository,
            StudentRepository studentRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }

    public AttendanceRecord registerAttendance(UUID classId, UUID studentId, LocalDate attendanceDate) {
        Optional<Class> gymClass = classRepository.findById(classId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (gymClass.isEmpty() || student.isEmpty()) {
            throw new RuntimeException("Aula ou aluno não encontrado");
        }

        Optional<AttendanceRecord> existingRecord = attendanceRecordRepository.findExistingAttendance(gymClass.get(), student.get(), attendanceDate);
        if (existingRecord.isPresent()) {
            throw new RuntimeException("Frequência já registrada para o aluno nesta aula e data.");
        }

        AttendanceRecord attendanceRecord = new AttendanceRecord(gymClass.get(), student.get(), attendanceDate, gymClass.get().getGym());
        return attendanceRecordRepository.save(attendanceRecord);
    }

    public List<AttendanceRecord> getAttendanceRecordsByClass(UUID classId) {
        return classRepository.findById(classId)
                .map(attendanceRecordRepository::findByGymClass)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    public List<AttendanceRecord> getAttendanceRecordsByStudent(UUID studentId) {
        return studentRepository.findById(studentId)
                .map(attendanceRecordRepository::findByStudent)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
