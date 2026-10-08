package com.canteen.observer;

import com.canteen.model.Order;
import com.canteen.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * DESIGN PATTERN: OBSERVER PATTERN (Concrete Observer)
 * =========================================================================
 * Purpose: Kitchen observer that listens to status notifications.
 * Automatically receives updates to prepare, complete, or deliver orders.
 * =========================================================================
 */
public class Kitchen implements OrderObserver {

    private final List<String> notifications = new ArrayList<>();

    @Override
    public void update(Order order, OrderStatus previousStatus) {
        String msg = "Order #" + order.getOrderId() 
                + " status changed from " + previousStatus 
                + " to " + order.getStatus() + ".";
        notifications.add(0, msg); // Newest first
        System.out.println("  [Kitchen Notification]: " + msg);
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }
}
