package com.coffeecorner.CharleneCoffeeCorner.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Snack extends Product {
    public Snack(String name, double price) {
        super(name, price);
    }
}
