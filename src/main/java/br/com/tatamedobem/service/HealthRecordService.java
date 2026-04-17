package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.HealthRecord;
import br.com.tatamedobem.dto.HealthRecordRequest;
import br.com.tatamedobem.dto.HealthRecordResponse;
import br.com.tatamedobem.repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HealthRecordService {

    private final HealthRecordRepository healthRecordRepository;
    private final StudentService studentService;

    @Transactional(readOnly = true)
    public List<HealthRecordResponse> findAll() {
        return healthRecordRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<HealthRecordResponse> findByStudent(Long studentId) {
        return healthRecordRepository.findByStudentIdOrderByRecordDateDesc(studentId).stream()
                .map(this::toResponse)
                .toList();
    }

    public HealthRecordResponse create(HealthRecordRequest request) {
        HealthRecord record = new HealthRecord();
        record.setStudent(studentService.getEntity(request.studentId()));
        record.setRecordDate(request.recordDate());
        record.setVaccinationsUpToDate(request.vaccinationsUpToDate());
        record.setLightInjuryNotes(request.lightInjuryNotes());
        record.setWeightKg(request.weightKg());
        record.setHeightMeters(request.heightMeters());
        record.setBmi(calculateBmi(request.weightKg(), request.heightMeters()));
        return toResponse(healthRecordRepository.save(record));
    }

    private BigDecimal calculateBmi(BigDecimal weightKg, BigDecimal heightMeters) {
        if (weightKg == null || heightMeters == null || BigDecimal.ZERO.compareTo(heightMeters) == 0) {
            return null;
        }
        return weightKg.divide(heightMeters.multiply(heightMeters), 2, RoundingMode.HALF_UP);
    }

    private HealthRecordResponse toResponse(HealthRecord record) {
        return new HealthRecordResponse(
                record.getId(),
                record.getStudent().getId(),
                record.getStudent().getFullName(),
                record.getRecordDate(),
                record.getVaccinationsUpToDate(),
                record.getLightInjuryNotes(),
                record.getWeightKg(),
                record.getHeightMeters(),
                record.getBmi()
        );
    }
}
