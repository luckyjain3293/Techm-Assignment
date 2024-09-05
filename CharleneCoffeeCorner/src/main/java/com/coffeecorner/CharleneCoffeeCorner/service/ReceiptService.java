package com.coffeecorner.CharleneCoffeeCorner.service;

import com.coffeecorner.CharleneCoffeeCorner.model.OrderItem;

import java.util.List;

public interface ReceiptService {
    void applyDiscountForBeverageSnackPair(List<OrderItem> items);
    void applyFifthBeverageFreeDiscount(List<OrderItem> items);
    String generateReceipt(List<OrderItem> items);
}
