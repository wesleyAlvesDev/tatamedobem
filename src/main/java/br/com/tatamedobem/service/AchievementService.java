package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.Achievement;
import br.com.tatamedobem.dto.AchievementRequest;
import br.com.tatamedobem.dto.AchievementResponse;
import br.com.tatamedobem.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final StudentService studentService;

    @Transactional(readOnly = true)
    public List<AchievementResponse> findAll() {
        return achievementRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AchievementResponse create(AchievementRequest request) {
        Achievement achievement = new Achievement();
        achievement.setStudent(studentService.getEntity(request.studentId()));
        achievement.setTitle(request.title());
        achievement.setDescription(request.description());
        achievement.setImageUrl(request.imageUrl());
        achievement.setCategory(request.category());
        achievement.setAchievementDate(request.achievementDate());
        return toResponse(achievementRepository.save(achievement));
    }

    private AchievementResponse toResponse(Achievement achievement) {
        return new AchievementResponse(
                achievement.getId(),
                achievement.getStudent().getId(),
                achievement.getStudent().getFullName(),
                achievement.getTitle(),
                achievement.getDescription(),
                achievement.getImageUrl(),
                achievement.getCategory(),
                achievement.getAchievementDate()
        );
    }
}
