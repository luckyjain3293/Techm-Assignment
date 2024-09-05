package com.coffeecorner.CharleneCoffeeCorner.service;

import com.coffeecorner.CharleneCoffeeCorner.model.OrderItem;

import java.util.List;

public interface OrderService {
    void addItem(OrderItem item);
    void clearOrder();
    void addBeverage(List<String> beverages);
    void addSnack(List<String> snacks);
    void addExtra(List<String> extras);
    List<OrderItem> getItems();
}
