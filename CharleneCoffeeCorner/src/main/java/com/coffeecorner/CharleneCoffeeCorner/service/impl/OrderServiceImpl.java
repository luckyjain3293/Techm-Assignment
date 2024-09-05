package com.coffeecorner.CharleneCoffeeCorner.service.impl;

import com.coffeecorner.CharleneCoffeeCorner.model.Beverage;
import com.coffeecorner.CharleneCoffeeCorner.model.Extra;
import com.coffeecorner.CharleneCoffeeCorner.model.OrderItem;
import com.coffeecorner.CharleneCoffeeCorner.model.Snack;
import com.coffeecorner.CharleneCoffeeCorner.service.OrderService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Service
public class OrderServiceImpl implements OrderService {

    private final List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void clearOrder() {
        log.debug("Clearing orders, after receipt generated");
        items.clear();
    }

    public void addBeverage(List<String> beverages){
        if (beverages != null) {
            for (String beverageType : beverages) {
                Beverage beverage = null;
                if(beverageType.equalsIgnoreCase("small")){
                    beverage = new Beverage("Small Coffee", "Small", 2.50);
                } else if (beverageType.equalsIgnoreCase("medium")) {
                    beverage = new Beverage("Medium Coffee", "Medium", 3.00);
                } else if (beverageType.equalsIgnoreCase("large")) {
                    beverage = new Beverage("Large Coffee", "Large", 3.50);
                } else if (beverageType.equalsIgnoreCase("orange juice")) {
                    beverage = new Beverage("Freshly Squeezed Orange Juice", "0.25L", 3.95);
                }
                addItem(new OrderItem(beverage));
            }
        }
    }

    public void addSnack(List<String> snacks){
        if (snacks != null) {
            for (String snack : snacks) {
                if (snack.equalsIgnoreCase("bacon roll")) {
                    addItem(new OrderItem(new Snack("Bacon Roll", 4.50)));
                }
            }
        }
    }

    public void addExtra(List<String> extras){
        if (extras != null) {
            for (String extra : extras) {
                Extra extraItem = null;
                if(extra.equalsIgnoreCase("extra milk")){
                    extraItem = new Extra("Extra Milk", 0.30);
                } else if (extra.equalsIgnoreCase("foamed milk")){
                    extraItem = new Extra("Foamed Milk", 0.50);
                } else if (extra.equalsIgnoreCase("special roast")) {
                    extraItem = new Extra("Special Roast Coffee", 0.90);
                }
                addItem(new OrderItem(extraItem));
            }
        }
    }
}
