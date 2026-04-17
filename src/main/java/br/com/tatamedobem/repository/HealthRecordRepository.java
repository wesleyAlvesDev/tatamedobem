package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {

    List<HealthRecord> findByStudentIdOrderByRecordDateDesc(Long studentId);
}
