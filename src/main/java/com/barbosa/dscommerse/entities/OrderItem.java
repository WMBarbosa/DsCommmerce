package com.barbosa.dscommerse.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Entity
@Table(name = "tb_order_item", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_id", "product_id" })
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }


    public void increaseQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            return;
        }
        this.quantity += amount;
    }

}
