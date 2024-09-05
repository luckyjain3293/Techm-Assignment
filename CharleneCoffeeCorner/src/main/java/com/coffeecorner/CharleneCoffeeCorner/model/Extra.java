package com.coffeecorner.CharleneCoffeeCorner.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Extra extends Product {
    public Extra(String name, double price) {
        super(name, price);
    }
}
