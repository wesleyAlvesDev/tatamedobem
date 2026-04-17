package br.com.tatamedobem.repository;

import br.com.tatamedobem.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findByQuantityAvailableLessThanEqual(Integer quantityAvailable);
}
