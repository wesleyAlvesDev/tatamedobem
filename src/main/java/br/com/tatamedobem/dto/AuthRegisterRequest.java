package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequest(
        @NotBlank String fullName,
        @NotBlank String cpf,
        @NotBlank @Size(min = 6, max = 100) String password,
        UserRole role
) {
}
