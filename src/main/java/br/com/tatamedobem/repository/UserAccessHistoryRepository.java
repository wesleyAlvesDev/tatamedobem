package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.UserAccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccessHistoryRepository extends JpaRepository<UserAccessHistory, Long> {

    List<UserAccessHistory> findByUserCpf(String cpf);
}
