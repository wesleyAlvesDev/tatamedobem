package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.BeltProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeltProgressRepository extends JpaRepository<BeltProgress, Long> {

    List<BeltProgress> findByStudentIdOrderByAchievedAtDesc(Long studentId);
}
