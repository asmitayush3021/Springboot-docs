package com.example.java_basics;

import java.util.ArrayList;
import java.util.List;

public class WeakOrder {
    public List<OrderItem> items=new ArrayList<>(); //❌ internal exposed
    public double totalAmount; // ❌ can be set inconsistently

}
