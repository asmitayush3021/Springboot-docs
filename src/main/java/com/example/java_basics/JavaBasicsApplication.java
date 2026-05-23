package com.example.java_basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class JavaBasicsApplication {

	public static void main(String[] args) {

        SpringApplication.run(JavaBasicsApplication.class, args);
        System.out.println("=== Demonstrating WEAK Encapsulation ===");

        WeakOrder weakOrder = new WeakOrder();
        OrderItem laptop=new OrderItem("Laptop",new BigDecimal("1200.00"));

        // Caller: order.items.add(item); order.totalAmount += item.price;
        weakOrder.items.add(laptop);
        weakOrder.totalAmount+=laptop.getTotalPrice().doubleValue();

        // Now business logic is scattered everywhere → bugs, inconsistency
        // A caller can bypass logic completely and break consistency:
        weakOrder.totalAmount=-500;// Malicious or accidental modification
        System.out.println("Weak Order Total (Inconsistent): $"+weakOrder.totalAmount);

        System.out.println("\n=== Demonstrating STRONG Encapsulation ===");
        Order strongOrder=new Order();
        OrderItem phone=new OrderItem("Smartphone",new BigDecimal("800.00"));
        OrderItem caseItem=new OrderItem("Phone Case",new BigDecimal("25"));

        // Add items through controlled channels
        strongOrder.addItem(phone);
        strongOrder.addItem(caseItem);

        System.out.println("Order Status: " + strongOrder.getStatus());
        System.out.println("Guaranteed Consistent Total: $" + strongOrder.getTotalAmount());
        System.out.println("Items in Order: " + strongOrder.getItems());

        // Attempting to modify the internal state externally via the list view
        try {
            strongOrder.getItems().add(new OrderItem("Free Item", BigDecimal.ZERO));
        } catch (UnsupportedOperationException e) {
            System.out.println("\n[Success] Blocked external modification attempt via getItems().");
        }

        // Lock the business state
        strongOrder.confirmOrder();
        System.out.println("Order Status updated to: " + strongOrder.getStatus());

        // Attempting to add an item after confirmation (Invariant protection)
        try {
            strongOrder.addItem(new OrderItem("Charger", new BigDecimal("30.00")));
        } catch (IllegalStateException e) {
            System.out.println("[Success] Blocked modification attempt: " + e.getMessage());
        }

	}

}
