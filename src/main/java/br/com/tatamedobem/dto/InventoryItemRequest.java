package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.InventoryCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventoryItemRequest(
        @NotBlank String itemName,
        @NotNull InventoryCategory category,
        String sizeLabel,
        @NotNull Integer quantityAvailable,
        @NotNull Integer minimumStock
) {
}
