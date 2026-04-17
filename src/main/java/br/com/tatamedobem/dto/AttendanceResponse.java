package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.AttendanceStatus;

import java.time.LocalDate;

public record AttendanceResponse(
        Long id,
        Long studentId,
        String studentName,
        LocalDate attendanceDate,
        AttendanceStatus status,
        String notes,
        Boolean guardianAlertTriggered
) {
}
