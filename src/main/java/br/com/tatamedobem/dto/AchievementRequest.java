package br.com.tatamedobem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AchievementRequest(
        @NotNull Long studentId,
        @NotBlank String title,
        String description,
        String imageUrl,
        String category,
        @NotNull LocalDate achievementDate
) {
}
