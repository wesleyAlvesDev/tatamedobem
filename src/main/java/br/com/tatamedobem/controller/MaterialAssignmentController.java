package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.MaterialAssignmentRequest;
import br.com.tatamedobem.dto.MaterialAssignmentResponse;
import br.com.tatamedobem.service.MaterialAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material-assignments")
@RequiredArgsConstructor
@Tag(name = "Doacoes")
public class MaterialAssignmentController {

    private final MaterialAssignmentService materialAssignmentService;

    @GetMapping
    @Operation(summary = "Listar materiais entregues aos alunos")
    public List<MaterialAssignmentResponse> findAll() {
        return materialAssignmentService.findAll();
    }

    @GetMapping("/resize-alerts")
    @Operation(summary = "Listar alunos que precisam de tamanho maior")
    public List<MaterialAssignmentResponse> findResizeAlerts() {
        return materialAssignmentService.findResizeAlerts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar entrega de material")
    public MaterialAssignmentResponse create(@Valid @RequestBody MaterialAssignmentRequest request) {
        return materialAssignmentService.create(request);
    }
}
