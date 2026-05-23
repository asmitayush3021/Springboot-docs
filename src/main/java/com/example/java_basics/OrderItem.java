package com.example.java_basics;

import java.math.BigDecimal;

public class OrderItem {
    private final String name;
    private final BigDecimal price;

    public OrderItem(String name,BigDecimal price) {
        this.name=name;
        this.price=price;
    }
    public BigDecimal getTotalPrice(){
        return this.price;
    }
    @Override
    public String toString(){
        return name +" ($"+price+")";
    }

}
