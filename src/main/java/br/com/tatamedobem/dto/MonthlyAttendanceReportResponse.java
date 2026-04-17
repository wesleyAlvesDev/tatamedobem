package br.com.tatamedobem.dto;

public record MonthlyAttendanceReportResponse(
        String month,
        long totalClasses,
        long totalPresents,
        long totalAbsences,
        double attendancePercentage,
        boolean meetsMinimumAttendance
) {
}
