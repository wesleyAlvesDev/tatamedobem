package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.BeltColor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BeltProgressRequest(
        @NotNull Long studentId,
        @NotNull BeltColor beltColor,
        Integer stripes,
        Integer stars,
        String behavioralGoal,
        Boolean goalCompleted,
        Boolean certificateIssued,
        LocalDate achievedAt
) {
}
