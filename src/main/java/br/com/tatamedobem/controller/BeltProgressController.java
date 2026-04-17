package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.BeltProgressRequest;
import br.com.tatamedobem.dto.BeltProgressResponse;
import br.com.tatamedobem.service.BeltProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/belt-progress")
@RequiredArgsConstructor
@Tag(name = "Faixas")
public class BeltProgressController {

    private final BeltProgressService beltProgressService;

    @GetMapping
    @Operation(summary = "Listar evolucoes de faixas")
    public List<BeltProgressResponse> findAll() {
        return beltProgressService.findAll();
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Buscar evolucao por aluno")
    public List<BeltProgressResponse> findByStudent(@PathVariable Long studentId) {
        return beltProgressService.findByStudent(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar nova evolucao de faixa")
    public BeltProgressResponse create(@Valid @RequestBody BeltProgressRequest request) {
        return beltProgressService.create(request);
    }
}
