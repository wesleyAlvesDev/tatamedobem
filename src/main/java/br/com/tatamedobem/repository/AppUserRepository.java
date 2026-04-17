package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
