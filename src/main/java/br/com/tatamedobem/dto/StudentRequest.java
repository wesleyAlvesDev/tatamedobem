package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.BeltColor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank String fullName,
        @NotBlank String documentNumber,
        @NotNull LocalDate birthDate,
        String photoUrl,
        String addressLine,
        String city,
        String state,
        String guardianName,
        String guardianPhone,
        String schoolName,
        String schoolGrade,
        String asthmaNotes,
        String epilepsyNotes,
        String allergyNotes,
        String previousInjuries,
        String donatedGiSize,
        Boolean active,
        BeltColor currentBelt
) {
}
