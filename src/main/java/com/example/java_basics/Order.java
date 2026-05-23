package com.example.java_basics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderItem> items=new ArrayList<>();
    private BigDecimal totalAmount=BigDecimal.ZERO;
    private OrderStatus status=OrderStatus.PENDING;

    public void addItem(OrderItem item){
        if(status!= OrderStatus.PENDING) {
            throw new IllegalStateException("Cannot modify confirmed order");
        }
        items.add(item);
        totalAmount=totalAmount.add(item.getTotalPrice()); //always consistent
    }
    public List<OrderItem> getItems(){
        return Collections.unmodifiableList(items); // ❌ can't modify external
    }
    // Added functionalities to read the state safely and transition business logic
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }
    public OrderStatus getStatus(){
        return status;
    }
    public void confirmOrder(){
        if(items.isEmpty()){
            throw new IllegalStateException("Cannot confirm an empty order");
        }
        this.status = OrderStatus.CONFIRMED;
    }
}
