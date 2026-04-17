package br.com.tatamedobem.dto;

public record DashboardSummaryResponse(
        long totalStudents,
        long activeStudents,
        long totalAttendanceAlerts,
        long inventoryAlerts,
        long resizeAlerts,
        long totalAchievements
) {
}
