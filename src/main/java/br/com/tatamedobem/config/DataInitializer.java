package br.com.tatamedobem.config;

import br.com.tatamedobem.domain.*;
import br.com.tatamedobem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final BeltProgressRepository beltProgressRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final MaterialAssignmentRepository materialAssignmentRepository;
    private final HealthRecordRepository healthRecordRepository;
    private final AchievementRepository achievementRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (appUserRepository.count() == 0) {
            AppUser admin = new AppUser();
            admin.setFullName("Administrador Tatame do Bem");
            admin.setCpf("11122233344");
            admin.setPasswordHash(passwordEncoder.encode("123456"));
            admin.setRole(UserRole.ADMIN);
            admin.setActive(true);
            appUserRepository.save(admin);
        }
    }
}
