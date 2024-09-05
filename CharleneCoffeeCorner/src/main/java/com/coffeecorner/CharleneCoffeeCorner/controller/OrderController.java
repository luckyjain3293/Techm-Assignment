package com.coffeecorner.CharleneCoffeeCorner.controller;

import com.coffeecorner.CharleneCoffeeCorner.model.OrderItem;
import com.coffeecorner.CharleneCoffeeCorner.service.OrderService;
import com.coffeecorner.CharleneCoffeeCorner.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/order")
    public String placeOrder(@RequestBody Map<String, List<String>> orderRequest) {
        log.info("Received order request");
        List<String> beverages = orderRequest.get("beverages");
        List<String> snacks = orderRequest.get("snacks");
        List<String> extras = orderRequest.get("extras");

        orderService.addBeverage(beverages);
        orderService.addSnack(snacks);
        orderService.addExtra(extras);

        List<OrderItem> items = orderService.getItems();
        log.info("Total number of item received : {}", (long) items.size());

        receiptService.applyDiscountForBeverageSnackPair(items);
        receiptService.applyFifthBeverageFreeDiscount(items);

        String receipt = receiptService.generateReceipt(items);

        orderService.clearOrder();

        return receipt;
    }
}