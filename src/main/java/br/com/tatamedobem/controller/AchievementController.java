package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.AchievementRequest;
import br.com.tatamedobem.dto.AchievementResponse;
import br.com.tatamedobem.service.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
@Tag(name = "Mural")
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping
    @Operation(summary = "Listar mural de conquistas")
    public List<AchievementResponse> findAll() {
        return achievementService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar conquista de aluno")
    public AchievementResponse create(@Valid @RequestBody AchievementRequest request) {
        return achievementService.create(request);
    }
}
