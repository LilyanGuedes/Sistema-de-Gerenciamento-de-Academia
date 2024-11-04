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

    // Método para registrar a frequência
    public AttendanceRecord registerAttendance(UUID classId, UUID studentId, LocalDate attendanceDate) {
        Optional<Class> gymClass = classRepository.findById(classId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (gymClass.isEmpty()) {
            throw new RuntimeException("Aula não encontrada");
        }

        if (student.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        AttendanceRecord attendanceRecord = new AttendanceRecord(
                gymClass.get(),
                student.get(),
                attendanceDate,
                gymClass.get().getGym()
        );

        return attendanceRecordRepository.save(attendanceRecord);
    }

    // Método para listar as frequências de uma aula específica
    public List<AttendanceRecord> getAttendanceRecordsByClass(UUID classId) {
        Optional<Class> gymClass = classRepository.findById(classId);

        if (gymClass.isEmpty()) {
            throw new RuntimeException("Aula não encontrada");
        }

        return attendanceRecordRepository.findByGymClass(gymClass.get());
    }

    // Método para listar as frequências de um aluno específico
    public List<AttendanceRecord> getAttendanceRecordsByStudent(UUID studentId) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        return attendanceRecordRepository.findByStudent(student.get());
    }
}
