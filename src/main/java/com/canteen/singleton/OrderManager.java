package com.canteen.singleton;

import com.canteen.factory.FoodFactory;
import com.canteen.factory.FoodItem;
import com.canteen.model.Order;
import com.canteen.observer.Customer;
import com.canteen.observer.Kitchen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * =========================================================================
 * DESIGN PATTERN: SINGLETON PATTERN
 * =========================================================================
 * Purpose: Ensures that only ONE instance of OrderManager exists throughout
 * the application lifecycle.
 * 
 * Why Singleton is used:
 * In a college canteen, all orders, order numbers, and kitchen updates must be 
 * tracked centrally. Having multiple order managers would lead to duplicate order IDs,
 * inconsistent data, and split state. The Singleton pattern guarantees a single
 * global point of access and a single shared state.
 * =========================================================================
 */
public class OrderManager {

    // 1. Private static instance variable (lazy initialization)
    private static OrderManager instance;

    // Internal storage for active orders
    private final Map<Integer, Order> orders = new HashMap<>();
    private int nextOrderId = 101; // Order IDs start from 101

    // Default observers attached to orders
    private final Customer defaultCustomer = new Customer("Student");
    private final Kitchen defaultKitchen = new Kitchen();

    // 2. Private constructor prevents instantiation outside this class using 'new OrderManager()'
    private OrderManager() {
        System.out.println("[SINGLETON INITIALIZATION]: OrderManager single instance created.");
    }

    // 3. Public static method to provide global access point
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Creates and stores a new order.
     * Uses Factory Pattern internally to create the FoodItem.
     * Attaches Customer and Kitchen Observers to the created Order.
     * 
     * @param foodChoice Menu option selected (1..6)
     * @param quantity Quantity of food item
     * @return Created Order object
     */
    public Order placeOrder(int foodChoice, int quantity) {
        // Factory Pattern: Delegate food object creation to FoodFactory
        FoodItem foodItem = FoodFactory.createFoodItem(foodChoice);

        int orderId = nextOrderId++;
        Order newOrder = new Order(orderId, foodItem, quantity);

        // Observer Pattern: Attach default observers
        newOrder.attach(defaultCustomer);
        newOrder.attach(defaultKitchen);

        // Save order in singleton map
        orders.put(orderId, newOrder);

        return newOrder;
    }

    /**
     * Retrieves an order by its ID.
     */
    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    /**
     * Returns a list of all placed orders.
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public Customer getDefaultCustomer() {
        return defaultCustomer;
    }

    public Kitchen getDefaultKitchen() {
        return defaultKitchen;
    }

    /**
     * Helper method to verify the Singleton pattern for Viva demonstration.
     */
    public static void verifySingletonPattern() {
        System.out.println("\n==================================================");
        System.out.println("     SINGLETON PATTERN VERIFICATION DEMO          ");
        System.out.println("==================================================");
        OrderManager manager1 = OrderManager.getInstance();
        OrderManager manager2 = OrderManager.getInstance();

        System.out.println("Hashcode of Manager 1: " + System.identityHashCode(manager1));
        System.out.println("Hashcode of Manager 2: " + System.identityHashCode(manager2));

        if (manager1 == manager2) {
            System.out.println("VERIFICATION RESULT: Both references point to the EXACT SAME instance!");
            System.out.println("Singleton Pattern is working correctly.");
        } else {
            System.out.println("VERIFICATION FAILED: Multiple instances exist!");
        }
        System.out.println("==================================================\n");
    }
}
