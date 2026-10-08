package com.canteen.model;

import com.canteen.factory.FoodItem;
import com.canteen.observer.OrderObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * DESIGN PATTERN: OBSERVER PATTERN (Subject / Observable)
 * =========================================================================
 * Order acts as the Subject in the Observer Pattern.
 * It maintains a list of observers (Customer, Kitchen) and notifies them
 * automatically whenever its status changes (PLACED -> PREPARING -> READY -> COMPLETED).
 * =========================================================================
 */
public class Order {
    private final int orderId;
    private final FoodItem foodItem;
    private final int quantity;
    private final double totalPrice;
    private OrderStatus status;

    // List of attached Observers
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(int orderId, FoodItem foodItem, int quantity) {
        this.orderId = orderId;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.totalPrice = foodItem.getPrice() * quantity;
        this.status = OrderStatus.PLACED;
    }

    /**
     * Attach an observer to this order.
     */
    public void attach(OrderObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Detach an observer from this order.
     */
    public void detach(OrderObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all attached observers when status changes.
     */
    public void notifyObservers(OrderStatus previousStatus) {
        for (OrderObserver observer : observers) {
            observer.update(this, previousStatus);
        }
    }

    /**
     * Updates the status of the order and triggers automatic notifications.
     */
    public void setStatus(OrderStatus newStatus) {
        if (this.status != newStatus) {
            OrderStatus previousStatus = this.status;
            this.status = newStatus;

            System.out.println("\n--------------------------------------------------");
            System.out.println("  >>> OBSERVER PATTERN TRIGGERED <<<");
            System.out.println("  Order #" + orderId + " Status Changed: " + previousStatus + " -> " + newStatus);
            System.out.println("--------------------------------------------------");
            notifyObservers(previousStatus);
            System.out.println("--------------------------------------------------\n");
        } else {
            System.out.println("Order #" + orderId + " is already in status: " + newStatus);
        }
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderObserver> getObservers() {
        return new ArrayList<>(observers);
    }

    public void displayOrderDetails() {
        System.out.println("========================================");
        System.out.println("            ORDER DETAILS               ");
        System.out.println("========================================");
        System.out.println("  Order ID     : #" + orderId);
        System.out.println("  Food Item    : " + foodItem.getName());
        System.out.println("  Category     : " + foodItem.getCategory());
        System.out.println("  Unit Price   : ₹" + String.format("%.2f", foodItem.getPrice()));
        System.out.println("  Quantity     : " + quantity);
        System.out.println("  Total Price  : ₹" + String.format("%.2f", totalPrice));
        System.out.println("  Order Status : " + status);
        System.out.println("========================================");
    }
}
