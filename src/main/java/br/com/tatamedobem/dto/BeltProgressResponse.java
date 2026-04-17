package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.BeltColor;

import java.time.LocalDate;

public record BeltProgressResponse(
        Long id,
        Long studentId,
        String studentName,
        BeltColor beltColor,
        Integer stripes,
        Integer stars,
        String behavioralGoal,
        Boolean goalCompleted,
        Boolean certificateIssued,
        LocalDate achievedAt
) {
}
