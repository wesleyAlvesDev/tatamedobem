package br.com.tatamedobem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HealthRecordResponse(
        Long id,
        Long studentId,
        String studentName,
        LocalDate recordDate,
        Boolean vaccinationsUpToDate,
        String lightInjuryNotes,
        BigDecimal weightKg,
        BigDecimal heightMeters,
        BigDecimal bmi
) {
}
