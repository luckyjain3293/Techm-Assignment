package com.coffeecorner.CharleneCoffeeCorner;

import com.coffeecorner.CharleneCoffeeCorner.model.Beverage;
import com.coffeecorner.CharleneCoffeeCorner.model.OrderItem;
import com.coffeecorner.CharleneCoffeeCorner.service.OrderService;
import com.coffeecorner.CharleneCoffeeCorner.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl();
    }

    @Test
    void testAddItem() {
        OrderItem item = new OrderItem(new Beverage("Small Coffee", "Small", 2.50));

        orderService.addItem(item);

        List<OrderItem> items = orderService.getItems();
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    void testClearOrder() {
        orderService.addItem(new OrderItem(new Beverage("Small Coffee", "Small", 2.50)));

        orderService.clearOrder();

        List<OrderItem> items = orderService.getItems();
        assertEquals(0, items.size());
    }
}
