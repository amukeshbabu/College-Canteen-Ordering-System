package com.canteen.observer;

import com.canteen.model.Order;
import com.canteen.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * DESIGN PATTERN: OBSERVER PATTERN (Concrete Observer)
 * =========================================================================
 * Purpose: Customer observer that listens to status notifications.
 * Automatically receives updates whenever their order changes status.
 * =========================================================================
 */
public class Customer implements OrderObserver {

    private final String customerName;
    private final List<String> notifications = new ArrayList<>();

    public Customer() {
        this.customerName = "Student";
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(Order order, OrderStatus previousStatus) {
        String msg = "Hello " + customerName 
                + ", your order #" + order.getOrderId() 
                + " (" + order.getFoodItem().getName() + " x" + order.getQuantity() 
                + ") is now " + order.getStatus() + ".";
        notifications.add(0, msg); // Newest first
        System.out.println("  [Customer Notification]: " + msg);
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

    public String getCustomerName() {
        return customerName;
    }
}
