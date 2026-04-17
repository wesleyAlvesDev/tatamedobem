package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.AttendanceStatus;
import br.com.tatamedobem.dto.DashboardSummaryResponse;
import br.com.tatamedobem.dto.SponsorReportResponse;
import br.com.tatamedobem.repository.AchievementRepository;
import br.com.tatamedobem.repository.AttendanceRecordRepository;
import br.com.tatamedobem.repository.InventoryItemRepository;
import br.com.tatamedobem.repository.MaterialAssignmentRepository;
import br.com.tatamedobem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

    private final StudentRepository studentRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final MaterialAssignmentRepository materialAssignmentRepository;
    private final AchievementRepository achievementRepository;

    public DashboardSummaryResponse getSummary() {
        long totalStudents = studentRepository.count();
        long activeStudents = studentRepository.findAll().stream().filter(student -> Boolean.TRUE.equals(student.getActive())).count();
        long inventoryAlerts = inventoryItemRepository.findAll().stream()
                .filter(item -> item.getQuantityAvailable() <= item.getMinimumStock())
                .count();
        return new DashboardSummaryResponse(
                totalStudents,
                activeStudents,
                attendanceRecordRepository.findByGuardianAlertTriggeredTrueOrderByAttendanceDateDesc().size(),
                inventoryAlerts,
                materialAssignmentRepository.findByRequiresResizeTrue().size(),
                achievementRepository.count()
        );
    }

    public SponsorReportResponse getSponsorReport() {
        LocalDate now = LocalDate.now();
        LocalDate semesterStart = now.getMonthValue() <= 6 ? LocalDate.of(now.getYear(), 1, 1) : LocalDate.of(now.getYear(), 7, 1);
        LocalDate semesterEnd = now.getMonthValue() <= 6 ? LocalDate.of(now.getYear(), 6, 30) : LocalDate.of(now.getYear(), 12, 31);
        long presents = attendanceRecordRepository.countByAttendanceDateBetweenAndStatus(semesterStart, semesterEnd, AttendanceStatus.PRESENT);
        long absences = attendanceRecordRepository.countByAttendanceDateBetweenAndStatus(semesterStart, semesterEnd, AttendanceStatus.ABSENT);
        long totalAttendance = presents + absences;
        double rate = totalAttendance == 0 ? 0 : (double) presents / totalAttendance * 100;
        long activeStudents = studentRepository.findAll().stream().filter(student -> Boolean.TRUE.equals(student.getActive())).count();
        long championshipWinners = achievementRepository.findAll().stream()
                .filter(achievement -> achievement.getCategory() != null && achievement.getCategory().toLowerCase().contains("campe"))
                .count();
        String message = "No semestre atendemos %d alunos, com %.1f%% de presenca media, %d conquistas em competicoes e %d alunos ativos."
                .formatted(studentRepository.count(), rate, championshipWinners, activeStudents);
        return new SponsorReportResponse(
                semesterStart + " a " + semesterEnd,
                studentRepository.count(),
                Math.round(rate * 10.0) / 10.0,
                championshipWinners,
                activeStudents,
                activeStudents,
                message
        );
    }
}
