package com.canteen;

import com.canteen.factory.*;
import com.canteen.model.Order;
import com.canteen.model.OrderStatus;
import com.canteen.observer.Customer;
import com.canteen.observer.Kitchen;
import com.canteen.singleton.OrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CanteenSystemTest {

    private OrderManager orderManager;

    @BeforeEach
    public void setUp() {
        orderManager = OrderManager.getInstance();
    }

    @Test
    @DisplayName("TC01 - Verify Food Menu Creation via FoodFactory")
    public void testTC01_DisplayFoodMenu() {
        FoodItem item1 = FoodFactory.createFoodItem(1);
        FoodItem item2 = FoodFactory.createFoodItem(2);
        FoodItem item3 = FoodFactory.createFoodItem(3);
        FoodItem item4 = FoodFactory.createFoodItem(4);
        FoodItem item5 = FoodFactory.createFoodItem(5);
        FoodItem item6 = FoodFactory.createFoodItem(6);

        assertEquals("Idli", item1.getName());
        assertEquals("Dosa", item2.getName());
        assertEquals("Fried Rice", item3.getName());
        assertEquals("Veg Meals", item4.getName());
        assertEquals("Tea", item5.getName());
        assertEquals("Coffee", item6.getName());
    }

    @Test
    @DisplayName("TC02 - Create Idli Order via OrderManager")
    public void testTC02_CreateIdliOrder() {
        Order order = orderManager.placeOrder(1, 3); // Idli x 3
        assertNotNull(order);
        assertEquals("Idli", order.getFoodItem().getName());
        assertEquals(3, order.getQuantity());
        assertEquals(90.0, order.getTotalPrice());
    }

    @Test
    @DisplayName("TC03 - Create Dosa Order via OrderManager")
    public void testTC03_CreateDosaOrder() {
        Order order = orderManager.placeOrder(2, 2); // Dosa x 2
        assertNotNull(order);
        assertEquals("Dosa", order.getFoodItem().getName());
        assertEquals(2, order.getQuantity());
        assertEquals(100.0, order.getTotalPrice());
    }

    @Test
    @DisplayName("TC04 - Calculate Total Price Accuracy")
    public void testTC04_CalculateTotalPrice() {
        Order order = orderManager.placeOrder(4, 5); // Veg Meals (₹100) x 5
        assertEquals(500.0, order.getTotalPrice(), 0.001);
    }

    @Test
    @DisplayName("TC05 - Sequential Order ID Generation")
    public void testTC05_GenerateOrderId() {
        Order order1 = orderManager.placeOrder(5, 1); // Tea
        Order order2 = orderManager.placeOrder(6, 1); // Coffee
        assertTrue(order2.getOrderId() > order1.getOrderId());
        assertEquals(order1.getOrderId() + 1, order2.getOrderId());
    }

    @Test
    @DisplayName("TC06 - Verify Singleton Instance Uniqueness")
    public void testTC06_VerifySingleton() {
        OrderManager instance1 = OrderManager.getInstance();
        OrderManager instance2 = OrderManager.getInstance();
        assertSame(instance1, instance2, "Both instances must refer to the exact same memory location.");
    }

    @Test
    @DisplayName("TC07 - Change Order Status Lifecycle")
    public void testTC07_ChangeOrderStatus() {
        Order order = orderManager.placeOrder(3, 1); // Fried Rice
        assertEquals(OrderStatus.PLACED, order.getStatus());

        order.setStatus(OrderStatus.PREPARING);
        assertEquals(OrderStatus.PREPARING, order.getStatus());

        order.setStatus(OrderStatus.READY);
        assertEquals(OrderStatus.READY, order.getStatus());

        order.setStatus(OrderStatus.COMPLETED);
        assertEquals(OrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    @DisplayName("TC08 - Verify Customer Notification Attached as Observer")
    public void testTC08_VerifyCustomerNotification() {
        Order order = orderManager.placeOrder(1, 2);
        boolean containsCustomer = order.getObservers().stream()
                .anyMatch(o -> o instanceof Customer);
        assertTrue(containsCustomer, "Order must contain Customer observer.");
    }

    @Test
    @DisplayName("TC09 - Verify Kitchen Notification Attached as Observer")
    public void testTC09_VerifyKitchenNotification() {
        Order order = orderManager.placeOrder(2, 1);
        boolean containsKitchen = order.getObservers().stream()
                .anyMatch(o -> o instanceof Kitchen);
        assertTrue(containsKitchen, "Order must contain Kitchen observer.");
    }

    @Test
    @DisplayName("TC10 - Complete Order Lifecycle and Retrieve from Manager")
    public void testTC10_CompleteOrder() {
        Order order = orderManager.placeOrder(6, 2); // Coffee x 2
        int id = order.getOrderId();
        order.setStatus(OrderStatus.COMPLETED);

        Order retrieved = orderManager.getOrder(id);
        assertNotNull(retrieved);
        assertEquals(OrderStatus.COMPLETED, retrieved.getStatus());
    }
}
