package br.com.tatamedobem.service;

import br.com.tatamedobem.domain.InventoryItem;
import br.com.tatamedobem.dto.InventoryItemRequest;
import br.com.tatamedobem.dto.InventoryItemResponse;
import br.com.tatamedobem.repository.InventoryItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Transactional(readOnly = true)
    public List<InventoryItemResponse> findAll() {
        return inventoryItemRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<InventoryItemResponse> findAlerts() {
        return inventoryItemRepository.findAll().stream()
                .filter(item -> item.getQuantityAvailable() <= item.getMinimumStock())
                .map(this::toResponse)
                .toList();
    }

    public InventoryItemResponse create(InventoryItemRequest request) {
        InventoryItem item = new InventoryItem();
        apply(item, request);
        return toResponse(inventoryItemRepository.save(item));
    }

    @Transactional(readOnly = true)
    public InventoryItem getEntity(Long id) {
        return inventoryItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item de estoque nao encontrado: " + id));
    }

    private void apply(InventoryItem item, InventoryItemRequest request) {
        item.setItemName(request.itemName());
        item.setCategory(request.category());
        item.setSizeLabel(request.sizeLabel());
        item.setQuantityAvailable(request.quantityAvailable());
        item.setMinimumStock(request.minimumStock());
    }

    private InventoryItemResponse toResponse(InventoryItem item) {
        return new InventoryItemResponse(
                item.getId(),
                item.getItemName(),
                item.getCategory(),
                item.getSizeLabel(),
                item.getQuantityAvailable(),
                item.getMinimumStock(),
                item.getQuantityAvailable() <= item.getMinimumStock()
        );
    }
}
