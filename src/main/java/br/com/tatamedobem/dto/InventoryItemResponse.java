package br.com.tatamedobem.dto;

import br.com.tatamedobem.domain.InventoryCategory;

public record InventoryItemResponse(
        Long id,
        String itemName,
        InventoryCategory category,
        String sizeLabel,
        Integer quantityAvailable,
        Integer minimumStock,
        boolean belowMinimum
) {
}
