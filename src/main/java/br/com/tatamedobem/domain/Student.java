package br.com.tatamedobem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String documentNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    private String photoUrl;
    private String addressLine;
    private String city;
    private String state;
    private String guardianName;
    private String guardianPhone;
    private String schoolName;
    private String schoolGrade;
    private String asthmaNotes;
    private String epilepsyNotes;
    private String allergyNotes;
    private String previousInjuries;
    private String donatedGiSize;
    private Boolean active = true;

    @Enumerated(EnumType.STRING)
    private BeltColor currentBelt = BeltColor.WHITE;
}
