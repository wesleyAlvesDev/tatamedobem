package br.com.tatamedobem.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HealthRecordRequest(
        @NotNull Long studentId,
        @NotNull LocalDate recordDate,
        Boolean vaccinationsUpToDate,
        String lightInjuryNotes,
        BigDecimal weightKg,
        BigDecimal heightMeters
) {
}
