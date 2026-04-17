package br.com.tatamedobem.controller;

import br.com.tatamedobem.dto.InventoryItemRequest;
import br.com.tatamedobem.dto.InventoryItemResponse;
import br.com.tatamedobem.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Estoque")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @Operation(summary = "Listar itens do estoque")
    public List<InventoryItemResponse> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/alerts")
    @Operation(summary = "Listar itens com estoque minimo")
    public List<InventoryItemResponse> findAlerts() {
        return inventoryService.findAlerts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar item no estoque")
    public InventoryItemResponse create(@Valid @RequestBody InventoryItemRequest request) {
        return inventoryService.create(request);
    }
}
