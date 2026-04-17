package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.BeltColor;

import java.time.LocalDate;

public record StudentResponse(
        Long id,
        String fullName,
        String documentNumber,
        LocalDate birthDate,
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
