package com.coffeecorner.CharleneCoffeeCorner;

import com.coffeecorner.CharleneCoffeeCorner.model.Beverage;
import com.coffeecorner.CharleneCoffeeCorner.model.Extra;
import com.coffeecorner.CharleneCoffeeCorner.model.OrderItem;
import com.coffeecorner.CharleneCoffeeCorner.model.Snack;
import com.coffeecorner.CharleneCoffeeCorner.service.ReceiptService;
import com.coffeecorner.CharleneCoffeeCorner.service.impl.ReceiptServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptServiceTest {
    @Autowired
    private ReceiptService receiptService;
    private List<OrderItem> orderItems;

    @BeforeEach
    void setUp() {
        receiptService = new ReceiptServiceImpl();
        orderItems = new ArrayList<>();
    }

    @Test
    void testApplyDiscountForBeverageSnackPair_OnePairOneExtraDiscounted() {

        orderItems.add(new OrderItem(new Beverage("Small Coffee", "Small", 2.50)));
        orderItems.add(new OrderItem(new Snack("Bacon Roll", 4.50)));
        orderItems.add(new OrderItem(new Extra("Extra Milk", 0.30)));
        orderItems.add(new OrderItem(new Extra("Foamed Milk", 0.50)));

        receiptService.applyDiscountForBeverageSnackPair(orderItems);

        int discountedExtras = 0;
        for (OrderItem item : orderItems) {
            if (item.getProduct() instanceof Extra && item.isFree()) {
                discountedExtras++;
            }
        }
        assertEquals(1, discountedExtras);
    }

    @Test
    void testApplyFifthBeverageFreeDiscount() {

        for (int i = 0; i < 5; i++) {
            orderItems.add(new OrderItem(new Beverage("Small Coffee", "Small", 2.50)));
        }

        receiptService.applyFifthBeverageFreeDiscount(orderItems);

        assertEquals(2.50, orderItems.get(4).getProduct().getPrice());
        assertEquals(true, orderItems.get(4).isFree());
    }

    @Test
    void testGenerateReceipt() {
        orderItems.add(new OrderItem(new Beverage("Small Coffee", "Small", 2.50)));
        orderItems.add(new OrderItem(new Snack("Bacon Roll", 4.50)));
        orderItems.add(new OrderItem(new Extra("Extra Milk", 0.30)));

        receiptService.applyDiscountForBeverageSnackPair(orderItems);

        String receipt = receiptService.generateReceipt(orderItems);

        String expectedReceipt = "---- Receipt ----\n" +
                "Small Coffee                    2.50 CHF\n" +
                "Bacon Roll                      4.50 CHF\n" +
                "Extra Milk                      0.00 CHF (Free)\n\n" +
                "Total:       7.00 CHF\n" +
                "-------------------\n";

        assertEquals(expectedReceipt, receipt);
    }
}