package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.AttendanceRecord;
import br.com.tatamedobem.domain.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    List<AttendanceRecord> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    List<AttendanceRecord> findByGuardianAlertTriggeredTrueOrderByAttendanceDateDesc();

    List<AttendanceRecord> findTop2ByStudentIdOrderByAttendanceDateDesc(Long studentId);

    long countByAttendanceDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, AttendanceStatus status);
}
