package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
