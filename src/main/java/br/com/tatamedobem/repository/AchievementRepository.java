package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
