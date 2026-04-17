package br.com.tatamedobem.dto;

import java.time.LocalDate;

public record AchievementResponse(
        Long id,
        Long studentId,
        String studentName,
        String title,
        String description,
        String imageUrl,
        String category,
        LocalDate achievementDate
) {
}
