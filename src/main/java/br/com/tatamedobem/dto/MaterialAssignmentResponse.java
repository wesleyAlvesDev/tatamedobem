package br.com.tatamedobem.dto;

import java.time.LocalDate;

public record MaterialAssignmentResponse(
        Long id,
        Long studentId,
        String studentName,
        Long inventoryItemId,
        String inventoryItemName,
        LocalDate assignedAt,
        String assignedSize,
        String notes,
        Boolean requiresResize
) {
}
