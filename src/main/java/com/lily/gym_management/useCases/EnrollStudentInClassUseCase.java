package com.lily.gym_management.useCases;

import com.lily.gym_management.entities.Class;
import com.lily.gym_management.entities.Student;
import com.lily.gym_management.repositories.ClassRepository;
import com.lily.gym_management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollStudentInClassUseCase {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public EnrollStudentInClassUseCase(
            ClassRepository classRepository,
            StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }

    public boolean enrollStudent(UUID classId, UUID studentId) {
        Optional<Class> aula = classRepository.findById(classId);
        Optional<Student> student = studentRepository.findById(studentId);

        if (aula.isEmpty()) {
            throw new RuntimeException("Aula não encontrada");
        }

        if (student.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        Student studentInstance = student.get();

        // Verifica se o pagamento do aluno está em dia
        if (!studentInstance.isPaymentUpToDate()) {
            throw new RuntimeException("Pagamento pendente. Não é possível inscrever o aluno na aula.");
        }

        Class classInstance = aula.get();

        // Inscreve o aluno se houver disponibilidade
        if (classInstance.enrollStudent(studentInstance)) {
            classRepository.save(classInstance); // Atualiza a aula com o aluno inscrito
            return true;
        } else {
            throw new RuntimeException("A turma está cheia, não é possível matricular o aluno.");
        }
    }
}
