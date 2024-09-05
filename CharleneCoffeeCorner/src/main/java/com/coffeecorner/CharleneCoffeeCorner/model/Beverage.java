package com.coffeecorner.CharleneCoffeeCorner.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Beverage extends Product {
    private String size;

    public Beverage(String name, String size, double price) {
        super(name, price);
        this.size = size;
    }
}
