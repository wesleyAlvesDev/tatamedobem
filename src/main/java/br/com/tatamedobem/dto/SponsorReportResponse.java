package br.com.tatamedobem.dto;

public record SponsorReportResponse(
        String semesterLabel,
        long studentsServed,
        double attendanceRate,
        long championshipWinners,
        long activeStudents,
        long zeroDropoutCount,
        String impactMessage
) {
}
