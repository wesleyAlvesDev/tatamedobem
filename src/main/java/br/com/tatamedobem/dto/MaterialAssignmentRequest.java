package br.com.tatamedobem.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MaterialAssignmentRequest(
        @NotNull Long studentId,
        @NotNull Long inventoryItemId,
        @NotNull LocalDate assignedAt,
        String assignedSize,
        String notes,
        Boolean requiresResize
) {
}
