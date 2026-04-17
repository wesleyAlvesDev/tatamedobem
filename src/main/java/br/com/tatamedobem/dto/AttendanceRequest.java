package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AttendanceRequest(
        @NotNull Long studentId,
        @NotNull LocalDate attendanceDate,
        @NotNull AttendanceStatus status,
        String notes
) {
}
