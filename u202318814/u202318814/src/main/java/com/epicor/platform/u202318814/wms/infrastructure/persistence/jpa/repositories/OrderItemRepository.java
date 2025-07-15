package com.epicor.platform.u202318814.wms.infrastructure.persistence.jpa.repositories;

import com.epicor.platform.u202318814.wms.domain.model.aggregates.OrderItem;
import com.epicor.platform.u202318814.wms.domain.model.valueobjects.OrderEpicorSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    boolean existsByOrderIdAndEpicorSku(Long orderId, OrderEpicorSku epicorSku);

}