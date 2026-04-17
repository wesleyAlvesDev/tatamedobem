package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.BeltProgress;
import br.com.tatamedobem.dto.BeltProgressRequest;
import br.com.tatamedobem.dto.BeltProgressResponse;
import br.com.tatamedobem.repository.BeltProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BeltProgressService {

    private final BeltProgressRepository beltProgressRepository;
    private final StudentService studentService;

    @Transactional(readOnly = true)
    public List<BeltProgressResponse> findAll() {
        return beltProgressRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<BeltProgressResponse> findByStudent(Long studentId) {
        return beltProgressRepository.findByStudentIdOrderByAchievedAtDesc(studentId).stream()
                .map(this::toResponse)
                .toList();
    }

    public BeltProgressResponse create(BeltProgressRequest request) {
        BeltProgress progress = new BeltProgress();
        progress.setStudent(studentService.getEntity(request.studentId()));
        progress.setBeltColor(request.beltColor());
        progress.setStripes(request.stripes());
        progress.setStars(request.stars());
        progress.setBehavioralGoal(request.behavioralGoal());
        progress.setGoalCompleted(Boolean.TRUE.equals(request.goalCompleted()));
        progress.setCertificateIssued(Boolean.TRUE.equals(request.certificateIssued()));
        progress.setAchievedAt(request.achievedAt());
        progress.getStudent().setCurrentBelt(request.beltColor());
        return toResponse(beltProgressRepository.save(progress));
    }

    private BeltProgressResponse toResponse(BeltProgress progress) {
        return new BeltProgressResponse(
                progress.getId(),
                progress.getStudent().getId(),
                progress.getStudent().getFullName(),
                progress.getBeltColor(),
                progress.getStripes(),
                progress.getStars(),
                progress.getBehavioralGoal(),
                progress.getGoalCompleted(),
                progress.getCertificateIssued(),
                progress.getAchievedAt()
        );
    }
}
