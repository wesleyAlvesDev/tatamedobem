package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.MaterialAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialAssignmentRepository extends JpaRepository<MaterialAssignment, Long> {

    List<MaterialAssignment> findByRequiresResizeTrue();
}
