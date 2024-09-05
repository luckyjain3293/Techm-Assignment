package com.coffeecorner.CharleneCoffeeCorner.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private Product product;
    private boolean isFree;

    public OrderItem(Product product) {
        this.product = product;
        this.isFree = false;
    }

}
