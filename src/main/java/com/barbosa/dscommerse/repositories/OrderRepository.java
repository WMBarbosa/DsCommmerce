package com.barbosa.dscommerse.repositories;


import com.barbosa.dscommerse.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT DISTINCT o
            FROM Order o
            JOIN FETCH o.items i
            JOIN FETCH i.id.product
            WHERE o.id = :id
    """)
    Optional<Order> findByIdWithItemsAndProducts(@Param("id") Long id);
}
