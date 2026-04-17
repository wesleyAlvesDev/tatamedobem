package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.UserRole;

public record AuthResponse(
        String token,
        String tokenType,
        Long userId,
        String fullName,
        String cpf,
        UserRole role
) {
}
