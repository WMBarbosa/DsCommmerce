package com.barbosa.dscommerse.entities;

import com.barbosa.dscommerse.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE ")
    private Instant moment;

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id.product ASC")
    private List<OrderItem> items = new ArrayList<>();

    public List<Product> getProducts () {
        return items.stream().map(OrderItem::getProduct).toList();
    }

    public void addItem(Product product, Integer quantity) {
        items.stream()
                .filter(i -> i.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(
                        i -> i.increaseQuantity(quantity),
                        () -> items.add(new OrderItem(this, product, quantity, product.getPrice()))
                );
    }

}
