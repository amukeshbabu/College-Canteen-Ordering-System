package com.canteen;

import com.canteen.factory.FoodFactory;
import com.canteen.factory.FoodItem;
import com.canteen.model.Order;
import com.canteen.model.OrderStatus;
import com.canteen.singleton.OrderManager;

import java.util.List;
import java.util.Scanner;

/**
 * =========================================================================
 * COLLEGE CANTEEN FOOD ORDERING SYSTEM USING DESIGN PATTERNS
 * =========================================================================
 * Main Entry Point for Console Demonstration.
 * Demonstrates:
 * 1. Factory Pattern (FoodFactory creating FoodItem objects)
 * 2. Singleton Pattern (OrderManager single global instance)
 * 3. Observer Pattern (Customer & Kitchen notifications on Order status changes)
 * =========================================================================
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Singleton Pattern: Access global OrderManager instance
        OrderManager manager = OrderManager.getInstance();

        boolean exit = false;

        System.out.println("==================================================");
        System.out.println("   WELCOME TO COLLEGE CANTEEN FOOD ORDERING SYSTEM");
        System.out.println("   [Demonstrating Factory, Singleton & Observer]   ");
        System.out.println("==================================================");

        while (!exit) {
            printMainMenu();
            System.out.print("Select an option (1-6): ");
            
            String input = scanner.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n Invalid input! Please enter a number between 1 and 6.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    displayFoodMenu();
                    break;
                case 2:
                    handlePlaceOrder(scanner, manager);
                    break;
                case 3:
                    handleViewOrder(scanner, manager);
                    break;
                case 4:
                    handleUpdateOrderStatus(scanner, manager);
                    break;
                case 5:
                    runAutomatedPatternDemo();
                    break;
                case 6:
                    exit = true;
                    System.out.println("\nThank you for using College Canteen System! Goodbye.");
                    break;
                default:
                    System.out.println("\n Invalid choice! Please select between 1 and 6.\n");
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n===== COLLEGE CANTEEN MENU =====");
        System.out.println("1. View Food Menu");
        System.out.println("2. Place Order");
        System.out.println("3. View Order Details");
        System.out.println("4. Update Order Status");
        System.out.println("5. Run Design Patterns Demo");
        System.out.println("6. Exit");
        System.out.println("================================");
    }

    private static void displayFoodMenu() {
        System.out.println("\n===== CANTEEN FOOD MENU =====");
        System.out.println("1. Idli        - ₹30.00 [Tiffin]");
        System.out.println("2. Dosa        - ₹50.00 [Tiffin]");
        System.out.println("3. Fried Rice  - ₹80.00 [Main Course]");
        System.out.println("4. Veg Meals   - ₹100.00 [Main Course]");
        System.out.println("5. Tea         - ₹15.00 [Beverage]");
        System.out.println("6. Coffee      - ₹20.00 [Beverage]");
        System.out.println("=============================");
    }

    private static void handlePlaceOrder(Scanner scanner, OrderManager manager) {
        displayFoodMenu();
        System.out.print("\nEnter Food Choice (1-6): ");
        int foodChoice;
        try {
            foodChoice = Integer.parseInt(scanner.nextLine().trim());
            if (foodChoice < 1 || foodChoice > 6) {
                System.out.println(" Invalid item choice. Please select from 1 to 6.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid input! Choice must be a number.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println(" Quantity must be at least 1.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid input! Quantity must be a number.");
            return;
        }

        System.out.println("\n[PATTERN STEP 1 - FACTORY]: Requesting FoodFactory to create food object for choice #" + foodChoice);
        // Singleton + Factory Patterns in action
        Order newOrder = manager.placeOrder(foodChoice, quantity);

        System.out.println("\n Order Created Successfully!");
        newOrder.displayOrderDetails();
    }

    private static void handleViewOrder(Scanner scanner, OrderManager manager) {
        List<Order> orders = manager.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("\n No orders placed yet.");
            return;
        }

        System.out.print("\nEnter Order ID to view (or press Enter to view all): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("\n=== ALL PLACED ORDERS ===");
            for (Order o : orders) {
                o.displayOrderDetails();
            }
        } else {
            try {
                int orderId = Integer.parseInt(input);
                Order order = manager.getOrder(orderId);
                if (order == null) {
                    System.out.println(" Order #" + orderId + " not found!");
                } else {
                    order.displayOrderDetails();
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid Order ID format.");
            }
        }
    }

    private static void handleUpdateOrderStatus(Scanner scanner, OrderManager manager) {
        List<Order> orders = manager.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("\n No active orders to update.");
            return;
        }

        System.out.print("\nEnter Order ID to update: ");
        int orderId;
        try {
            orderId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid Order ID.");
            return;
        }

        Order order = manager.getOrder(orderId);
        if (order == null) {
            System.out.println(" Order #" + orderId + " does not exist.");
            return;
        }

        System.out.println("\nCurrent Order Status: " + order.getStatus());
        System.out.println("Select New Status:");
        System.out.println("1. PREPARING");
        System.out.println("2. READY");
        System.out.println("3. COMPLETED");
        System.out.print("Enter choice (1-3): ");

        int statusChoice;
        try {
            statusChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(" Invalid selection.");
            return;
        }

        OrderStatus newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = OrderStatus.PREPARING;
                break;
            case 2:
                newStatus = OrderStatus.READY;
                break;
            case 3:
                newStatus = OrderStatus.COMPLETED;
                break;
            default:
                System.out.println(" Invalid choice! Status unchanged.");
                return;
        }

        // Trigger Observer Pattern!
        order.setStatus(newStatus);
    }

    /**
     * Automated complete demonstration for Viva and Faculty review.
     */
    public static void runAutomatedPatternDemo() {
        System.out.println("\n==================================================================");
        System.out.println("     AUTOMATED DESIGN PATTERN DEMONSTRATION & FACULTY REVIEW      ");
        System.out.println("==================================================================");

        // 1. Verify SINGLETON PATTERN
        System.out.println("\n--- DEMO 1: SINGLETON PATTERN ---");
        OrderManager.verifySingletonPattern();

        // 2. Verify FACTORY PATTERN
        System.out.println("--- DEMO 2: FACTORY PATTERN ---");
        System.out.println("Creating food objects via FoodFactory.createFoodItem(...) without using 'new' directly:");
        FoodItem item1 = FoodFactory.createFoodItem(2); // Dosa
        FoodItem item2 = FoodFactory.createFoodItem(5); // Tea
        System.out.println(" Factory created: " + item1.getName() + " (Class: " + item1.getClass().getSimpleName() + ", Price: ₹" + item1.getPrice() + ")");
        System.out.println(" Factory created: " + item2.getName() + " (Class: " + item2.getClass().getSimpleName() + ", Price: ₹" + item2.getPrice() + ")");

        // 3. Verify OBSERVER PATTERN
        System.out.println("\n--- DEMO 3: OBSERVER PATTERN ---");
        System.out.println("Placing order for Dosa (x2)...");
        OrderManager manager = OrderManager.getInstance();
        Order order = manager.placeOrder(2, 2);
        order.displayOrderDetails();

        System.out.println("\nUpdating order status: PLACED -> PREPARING...");
        order.setStatus(OrderStatus.PREPARING);

        System.out.println("Updating order status: PREPARING -> READY...");
        order.setStatus(OrderStatus.READY);

        System.out.println("Updating order status: READY -> COMPLETED...");
        order.setStatus(OrderStatus.COMPLETED);

        System.out.println("==================================================================");
        System.out.println("     AUTOMATED DEMONSTRATION COMPLETED SUCCESSFULLY               ");
        System.out.println("==================================================================\n");
    }
}
