package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.HealthRecordRequest;
import br.com.tatamedobem.dto.HealthRecordResponse;
import br.com.tatamedobem.service.HealthRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-records")
@RequiredArgsConstructor
@Tag(name = "Saude")
public class HealthRecordController {

    private final HealthRecordService healthRecordService;

    @GetMapping
    @Operation(summary = "Listar fichas medicas")
    public List<HealthRecordResponse> findAll() {
        return healthRecordService.findAll();
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Buscar fichas medicas por aluno")
    public List<HealthRecordResponse> findByStudent(@PathVariable Long studentId) {
        return healthRecordService.findByStudent(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar acompanhamento de saude")
    public HealthRecordResponse create(@Valid @RequestBody HealthRecordRequest request) {
        return healthRecordService.create(request);
    }
}
