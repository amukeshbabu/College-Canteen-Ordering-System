package com.canteen.controller;

import com.canteen.factory.FoodFactory;
import com.canteen.factory.FoodItem;
import com.canteen.model.CartItem;
import com.canteen.model.Order;
import com.canteen.model.OrderStatus;
import com.canteen.singleton.OrderManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller providing web endpoints for the College Canteen Food Ordering Application.
 * Integrates Thymeleaf templates with Singleton (OrderManager), Factory (FoodFactory),
 * and Observer (Customer/Kitchen) patterns.
 */
@Controller
public class CanteenWebController {

    @SuppressWarnings("unchecked")
    private List<CartItem> getSessionCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    /**
     * Helper to load common design pattern metrics into model.
     */
    private void populatePatternInfo(Model model) {
        OrderManager manager = OrderManager.getInstance();
        model.addAttribute("singletonHash", System.identityHashCode(manager));
        model.addAttribute("totalOrders", manager.getAllOrders().size());
        model.addAttribute("customerNotifications", manager.getDefaultCustomer().getNotifications());
        model.addAttribute("kitchenNotifications", manager.getDefaultKitchen().getNotifications());
    }

    // 1. Home / Dashboard Page -> /
    @GetMapping("/")
    public String dashboard(Model model) {
        OrderManager manager = OrderManager.getInstance();
        List<Order> allOrders = manager.getAllOrders();

        long activeCount = allOrders.stream().filter(o -> o.getStatus() != OrderStatus.COMPLETED).count();
        long completedCount = allOrders.stream().filter(o -> o.getStatus() == OrderStatus.COMPLETED).count();

        model.addAttribute("activeOrdersCount", activeCount);
        model.addAttribute("completedOrdersCount", completedCount);
        model.addAttribute("orders", allOrders);
        populatePatternInfo(model);
        return "dashboard";
    }

    // 2. Food Menu Page -> /menu
    @GetMapping("/menu")
    public String menu(Model model) {
        List<FoodMenuItemDTO> menuItems = new ArrayList<>();
        menuItems.add(new FoodMenuItemDTO(1, FoodFactory.createFoodItem(1)));
        menuItems.add(new FoodMenuItemDTO(2, FoodFactory.createFoodItem(2)));
        menuItems.add(new FoodMenuItemDTO(3, FoodFactory.createFoodItem(3)));
        menuItems.add(new FoodMenuItemDTO(4, FoodFactory.createFoodItem(4)));
        menuItems.add(new FoodMenuItemDTO(5, FoodFactory.createFoodItem(5)));
        menuItems.add(new FoodMenuItemDTO(6, FoodFactory.createFoodItem(6)));

        model.addAttribute("menuItems", menuItems);
        populatePatternInfo(model);
        return "menu";
    }

    // 3. Cart Page -> /cart
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        List<CartItem> cart = getSessionCart(session);
        double grandTotal = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();

        model.addAttribute("cart", cart);
        model.addAttribute("grandTotal", grandTotal);
        populatePatternInfo(model);
        return "cart";
    }

    // Add item to cart
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("foodChoice") int foodChoice,
                            @RequestParam(value = "quantity", defaultValue = "1") int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (quantity < 1) quantity = 1;

        List<CartItem> cart = getSessionCart(session);
        FoodItem item = FoodFactory.createFoodItem(foodChoice); // Factory Pattern

        boolean existing = false;
        for (CartItem ci : cart) {
            if (ci.getFoodChoice() == foodChoice) {
                ci.setQuantity(ci.getQuantity() + quantity);
                existing = true;
                break;
            }
        }

        if (!existing) {
            cart.add(new CartItem(foodChoice, item, quantity));
        }

        redirectAttributes.addFlashAttribute("successMsg", "Added " + quantity + "x " + item.getName() + " to your cart!");
        return "redirect:/menu";
    }

    // Quick Place Order from Menu directly
    @PostMapping("/order/place-direct")
    public String placeDirectOrder(@RequestParam("foodChoice") int foodChoice,
                                   @RequestParam(value = "quantity", defaultValue = "1") int quantity,
                                   RedirectAttributes redirectAttributes) {
        if (quantity < 1) quantity = 1;
        OrderManager manager = OrderManager.getInstance(); // Singleton Pattern
        Order order = manager.placeOrder(foodChoice, quantity); // Factory + Observer Patterns

        redirectAttributes.addFlashAttribute("successMsg", 
                "Order #" + order.getOrderId() + " (" + order.getFoodItem().getName() + " x" + quantity + ") placed successfully!");
        return "redirect:/orders";
    }

    // Remove item from cart
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("foodChoice") int foodChoice, HttpSession session) {
        List<CartItem> cart = getSessionCart(session);
        cart.removeIf(ci -> ci.getFoodChoice() == foodChoice);
        return "redirect:/cart";
    }

    // Clear entire cart
    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        List<CartItem> cart = getSessionCart(session);
        cart.clear();
        return "redirect:/cart";
    }

    // Checkout / Place Order from Cart
    @PostMapping("/cart/checkout")
    public String checkout(HttpSession session, RedirectAttributes redirectAttributes) {
        List<CartItem> cart = getSessionCart(session);
        if (cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMsg", "Your cart is empty!");
            return "redirect:/cart";
        }

        OrderManager manager = OrderManager.getInstance(); // Singleton Pattern
        List<Integer> orderIds = new ArrayList<>();

        for (CartItem ci : cart) {
            // Singleton + Factory + Observer Patterns in placeOrder
            Order newOrder = manager.placeOrder(ci.getFoodChoice(), ci.getQuantity());
            orderIds.add(newOrder.getOrderId());
        }

        cart.clear();
        redirectAttributes.addFlashAttribute("successMsg", 
                "Placed " + orderIds.size() + " order(s) successfully! Order IDs: #" + 
                orderIds.stream().map(Object::toString).collect(Collectors.joining(", #")));
        return "redirect:/orders";
    }

    // 4. Current Orders Page -> /orders
    @GetMapping("/orders")
    public String currentOrders(Model model) {
        OrderManager manager = OrderManager.getInstance();
        List<Order> activeOrders = manager.getAllOrders().stream()
                .filter(o -> o.getStatus() != OrderStatus.COMPLETED)
                .collect(Collectors.toList());

        model.addAttribute("orders", activeOrders);
        populatePatternInfo(model);
        return "orders";
    }

    // 5. Order History Page -> /history
    @GetMapping("/history")
    public String orderHistory(Model model) {
        OrderManager manager = OrderManager.getInstance();
        List<Order> allOrders = manager.getAllOrders();
        double totalRevenue = allOrders.stream().mapToDouble(Order::getTotalPrice).sum();

        model.addAttribute("orders", allOrders);
        model.addAttribute("totalRevenue", totalRevenue);
        populatePatternInfo(model);
        return "history";
    }

    // 6. Kitchen Dashboard Page -> /kitchen
    @GetMapping("/kitchen")
    public String kitchen(Model model) {
        OrderManager manager = OrderManager.getInstance();
        List<Order> kitchenOrders = manager.getAllOrders().stream()
                .filter(o -> o.getStatus() != OrderStatus.COMPLETED)
                .collect(Collectors.toList());

        model.addAttribute("orders", kitchenOrders);
        model.addAttribute("allOrders", manager.getAllOrders());
        populatePatternInfo(model);
        return "kitchen";
    }

    // Update order status from Kitchen Dashboard -> Triggers OBSERVER PATTERN!
    @PostMapping("/kitchen/update-status")
    public String updateStatus(@RequestParam("orderId") int orderId,
                               @RequestParam("newStatus") String newStatusStr,
                               RedirectAttributes redirectAttributes) {
        OrderManager manager = OrderManager.getInstance();
        Order order = manager.getOrder(orderId);

        if (order != null) {
            try {
                OrderStatus newStatus = OrderStatus.valueOf(newStatusStr);
                // Trigger Observer Pattern notifyObservers(...)
                order.setStatus(newStatus);
                redirectAttributes.addFlashAttribute("successMsg", 
                        "Order #" + orderId + " status updated to " + newStatus + "! Observer notifications dispatched.");
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("errorMsg", "Invalid status value.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Order #" + orderId + " not found!");
        }

        return "redirect:/kitchen";
    }

    // Trigger Viva Automated Pattern Demo
    @PostMapping("/demo/run")
    public String runPatternDemo(RedirectAttributes redirectAttributes) {
        OrderManager.verifySingletonPattern();
        OrderManager manager = OrderManager.getInstance();
        
        // Place demo order
        Order demoOrder = manager.placeOrder(2, 2); // Dosa x 2
        demoOrder.setStatus(OrderStatus.PREPARING);
        demoOrder.setStatus(OrderStatus.READY);
        demoOrder.setStatus(OrderStatus.COMPLETED);

        redirectAttributes.addFlashAttribute("successMsg", 
                "Automated Pattern Demo Executed! Verified Singleton, Factory (Dosa x2), and Observer transitions.");
        return "redirect:/";
    }

    /**
     * DTO for displaying food item menu list conveniently in Thymeleaf.
     */
    public static class FoodMenuItemDTO {
        private final int choice;
        private final FoodItem item;

        public FoodMenuItemDTO(int choice, FoodItem item) {
            this.choice = choice;
            this.item = item;
        }

        public int getChoice() { return choice; }
        public FoodItem getItem() { return item; }
    }
}
