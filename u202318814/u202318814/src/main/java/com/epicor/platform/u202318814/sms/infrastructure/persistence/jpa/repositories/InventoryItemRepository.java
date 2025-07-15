package com.epicor.platform.u202318814.sms.infrastructure.persistence.jpa.repositories;

import com.epicor.platform.u202318814.sms.domain.model.aggregates.InventoryItem;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByEpicorSku(EpicorSku epicorSku);
    boolean existsByEpicorSku(EpicorSku epicorSku);
}
