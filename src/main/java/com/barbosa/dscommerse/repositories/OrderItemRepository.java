package com.barbosa.dscommerse.repositories;

import com.barbosa.dscommerse.entities.OrderItem;
import com.barbosa.dscommerse.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
