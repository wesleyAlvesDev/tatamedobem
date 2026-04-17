package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.AttendanceRecord;
import br.com.tatamedobem.domain.AttendanceStatus;
import br.com.tatamedobem.domain.Student;
import br.com.tatamedobem.dto.AttendanceRequest;
import br.com.tatamedobem.dto.AttendanceResponse;
import br.com.tatamedobem.dto.MonthlyAttendanceReportResponse;
import br.com.tatamedobem.repository.AttendanceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceService {

    private final AttendanceRecordRepository attendanceRecordRepository;
    private final StudentService studentService;

    @Transactional(readOnly = true)
    public List<AttendanceResponse> findAll() {
        return attendanceRecordRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public AttendanceResponse create(AttendanceRequest request) {
        Student student = studentService.getEntity(request.studentId());
        AttendanceRecord record = new AttendanceRecord();
        record.setStudent(student);
        record.setAttendanceDate(request.attendanceDate());
        record.setStatus(request.status());
        record.setNotes(request.notes());
        record.setGuardianAlertTriggered(shouldTriggerAlert(request.studentId(), request.status()));
        return toResponse(attendanceRecordRepository.save(record));
    }

    @Transactional(readOnly = true)
    public List<AttendanceResponse> findAlerts() {
        return attendanceRecordRepository.findByGuardianAlertTriggeredTrueOrderByAttendanceDateDesc().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MonthlyAttendanceReportResponse getMonthlyReport(YearMonth month) {
        var start = month.atDay(1);
        var end = month.atEndOfMonth();
        long presents = attendanceRecordRepository.countByAttendanceDateBetweenAndStatus(start, end, AttendanceStatus.PRESENT);
        long absences = attendanceRecordRepository.countByAttendanceDateBetweenAndStatus(start, end, AttendanceStatus.ABSENT);
        long total = presents + absences;
        double percentage = total == 0 ? 0 : (double) presents / total * 100;
        return new MonthlyAttendanceReportResponse(
                month.toString(),
                total,
                presents,
                absences,
                Math.round(percentage * 100.0) / 100.0,
                percentage >= 70
        );
    }

    private boolean shouldTriggerAlert(Long studentId, AttendanceStatus status) {
        if (status != AttendanceStatus.ABSENT) {
            return false;
        }
        List<AttendanceRecord> lastRecords = attendanceRecordRepository.findTop2ByStudentIdOrderByAttendanceDateDesc(studentId);
        return !lastRecords.isEmpty() && lastRecords.get(0).getStatus() == AttendanceStatus.ABSENT;
    }

    private AttendanceResponse toResponse(AttendanceRecord record) {
        return new AttendanceResponse(
                record.getId(),
                record.getStudent().getId(),
                record.getStudent().getFullName(),
                record.getAttendanceDate(),
                record.getStatus(),
                record.getNotes(),
                record.getGuardianAlertTriggered()
        );
    }
}
