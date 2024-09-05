package com.coffeecorner.CharleneCoffeeCorner.service.impl;

import com.coffeecorner.CharleneCoffeeCorner.model.*;
import com.coffeecorner.CharleneCoffeeCorner.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ReceiptServiceImpl implements ReceiptService {

    public void applyDiscountForBeverageSnackPair(List<OrderItem> items) {
        int beverageCount = (int) items.stream().filter(item -> item.getProduct() instanceof Beverage).count();
        int snackCount = (int) items.stream().filter(item -> item.getProduct() instanceof Snack).count();

        // Calculate pairs of beverage and snack
        int discountedExtras = Math.min(beverageCount, snackCount);

        int extrasCount = 0;
        for (OrderItem item : items) {
            if (item.getProduct() instanceof Extra && extrasCount < discountedExtras) {
                item.setFree(true);
                extrasCount++;
            }
        }
        log.info("Number of discount items in Extras: {}", discountedExtras);
    }

    public void applyFifthBeverageFreeDiscount(List<OrderItem> items) {
        int beverageCount = 0;

        for (OrderItem item : items) {
            if (item.getProduct() instanceof Beverage) {
                beverageCount++;
                if (beverageCount % 5 == 0) {
                    item.setFree(true);  // Mark every 5th beverage as free
                }
            }
        }
    }

    public String generateReceipt(List<OrderItem> items) {
        StringBuilder receipt = new StringBuilder("---- Receipt ----\n");
        double total = 0.0;

        for (OrderItem item : items) {
            Product product = item.getProduct();
            if(null!=product) {
                if (item.isFree()) {
                    receipt.append(String.format("%-25s %10.2f CHF (Free)\n", product.getName(), 0.0));
                } else {
                    receipt.append(String.format("%-25s %10.2f CHF\n", product.getName(), product.getPrice()));
                    total += product.getPrice();
                }
            }
        }

        receipt.append(String.format("\nTotal: %10.2f CHF\n", total));
        receipt.append("-------------------\n");

        return receipt.toString();
    }
}
