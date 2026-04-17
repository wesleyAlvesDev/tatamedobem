package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.Student;
import br.com.tatamedobem.dto.StudentRequest;
import br.com.tatamedobem.dto.StudentResponse;
import br.com.tatamedobem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    public StudentResponse create(StudentRequest request) {
        Student student = new Student();
        apply(student, request);
        return toResponse(studentRepository.save(student));
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = getEntity(id);
        apply(student, request);
        return toResponse(studentRepository.save(student));
    }

    public void delete(Long id) {
        studentRepository.delete(getEntity(id));
    }

    @Transactional(readOnly = true)
    public Student getEntity(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado: " + id));
    }

    private void apply(Student student, StudentRequest request) {
        student.setFullName(request.fullName());
        student.setDocumentNumber(request.documentNumber());
        student.setBirthDate(request.birthDate());
        student.setPhotoUrl(request.photoUrl());
        student.setAddressLine(request.addressLine());
        student.setCity(request.city());
        student.setState(request.state());
        student.setGuardianName(request.guardianName());
        student.setGuardianPhone(request.guardianPhone());
        student.setSchoolName(request.schoolName());
        student.setSchoolGrade(request.schoolGrade());
        student.setAsthmaNotes(request.asthmaNotes());
        student.setEpilepsyNotes(request.epilepsyNotes());
        student.setAllergyNotes(request.allergyNotes());
        student.setPreviousInjuries(request.previousInjuries());
        student.setDonatedGiSize(request.donatedGiSize());
        student.setActive(request.active() == null ? Boolean.TRUE : request.active());
        if (request.currentBelt() != null) {
            student.setCurrentBelt(request.currentBelt());
        }
    }

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFullName(),
                student.getDocumentNumber(),
                student.getBirthDate(),
                student.getPhotoUrl(),
                student.getAddressLine(),
                student.getCity(),
                student.getState(),
                student.getGuardianName(),
                student.getGuardianPhone(),
                student.getSchoolName(),
                student.getSchoolGrade(),
                student.getAsthmaNotes(),
                student.getEpilepsyNotes(),
                student.getAllergyNotes(),
                student.getPreviousInjuries(),
                student.getDonatedGiSize(),
                student.getActive(),
                student.getCurrentBelt()
        );
    }
}
