package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.UserRole;

public record UserResponse(
        Long id,
        String fullName,
        String cpf,
        UserRole role,
        Boolean active
) {
}
