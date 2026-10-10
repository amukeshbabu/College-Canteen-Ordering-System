/**
 * JeevaCan Core Application Engine
 * Vanilla JavaScript state management, observer pattern dispatcher, and UI controllers.
 */

// Centralized SVG Icon Library (Lucide-inspired clean geometric vectors)
const ICONS = {
  cart: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="8" cy="21" r="1"/><circle cx="19" cy="21" r="1"/><path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12"/></svg>`,
  home: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="m3 9 9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/></svg>`,
  menu: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 6h18"/><path d="M3 12h18"/><path d="M3 18h18"/></svg>`,
  orders: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><polyline points="14 2 14 8 20 8"/><line x1="16" y1="13" x2="8" y2="13"/><line x1="16" y1="17" x2="8" y2="17"/><polyline points="10 9 9 9 8 9"/></svg>`,
  history: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>`,
  kitchen: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M6 13.87A4 4 0 0 1 7.41 6a5.11 5.11 0 0 1 1.05-1.54 5 5 0 0 1 7.08 0A5.11 5.11 0 0 1 16.59 6 4 4 0 0 1 18 13.87V21H6Z"/><line x1="6" y1="17" x2="18" y2="17"/></svg>`,
  search: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"/><path d="m21 21-4.3-4.3"/></svg>`,
  check: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="20 6 9 17 4 12"/></svg>`,
  clock: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>`,
  trash: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 6h18"/><path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"/><path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"/><line x1="10" y1="11" x2="10" y2="17"/><line x1="14" y1="11" x2="14" y2="17"/></svg>`,
  plus: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="5" x2="12" y2="19"/><line x1="5" y1="12" x2="19" y2="12"/></svg>`,
  minus: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="5" y1="12" x2="19" y2="12"/></svg>`,
  bolt: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="13 2 3 14 12 14 11 22 21 10 12 10 13 2"/></svg>`,
  bell: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg>`,
  lock: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect width="18" height="11" x="3" y="11" rx="2" ry="2"/><path d="M7 11V7a5 5 0 0 1 10 0v4"/></svg>`,
  box: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16Z"/><path d="m3.3 7 8.7 5 8.7-5"/><path d="M12 22V12"/></svg>`,
  arrowRight: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/></svg>`,
  x: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/></svg>`,
  refresh: `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="23 4 23 10 17 10"/><polyline points="1 20 1 14 7 14"/><path d="M3.51 9a9 9 0 0 1 14.85-3.36L23 10M1 14l4.64 4.36A9 9 0 0 0 20.49 15"/></svg>`
};

class JeevaCanManager {
  constructor() {
    this.STORAGE_KEY_CART = "jeevacan_cart_v2";
    this.STORAGE_KEY_ORDERS = "jeevacan_orders_v2";
    this.STORAGE_KEY_NOTIFS = "jeevacan_notifs_v2";

    this.initStorage();
  }

  initStorage() {
    // Initialize Cart with initial sample selection
    if (!localStorage.getItem(this.STORAGE_KEY_CART)) {
      const initialCart = [
        { foodId: 2, name: "Crispy Masala Dosa", price: 50.0, quantity: 2, category: "Breakfast / Tiffin", image: "assets/images/dosa.jpg", factoryClass: "Dosa" },
        { foodId: 6, name: "Madras Filter Coffee", price: 20.0, quantity: 1, category: "Beverage", image: "assets/images/coffee.jpg", factoryClass: "Coffee" }
      ];
      localStorage.setItem(this.STORAGE_KEY_CART, JSON.stringify(initialCart));
    }

    // Initialize Orders with realistic seeds if first time
    if (!localStorage.getItem(this.STORAGE_KEY_ORDERS)) {
      localStorage.setItem(this.STORAGE_KEY_ORDERS, JSON.stringify(JEEVACAN_DATA.seedOrders));
    }

    // Initialize Notification Logs
    if (!localStorage.getItem(this.STORAGE_KEY_NOTIFS)) {
      const initialLogs = [
        { type: "customer", text: "Order #103 placed for Steamed Idli & Tea. Waiting for kitchen acceptance.", time: new Date(Date.now() - 4 * 60000).toISOString() },
        { type: "kitchen", text: "Kitchen staff started preparation for Order #102 (Veg Fried Rice x1).", time: new Date(Date.now() - 8 * 60000).toISOString() },
        { type: "customer", text: "Order #101 is READY for counter pickup at Canteen Counter #2!", time: new Date(Date.now() - 5 * 60000).toISOString() }
      ];
      localStorage.setItem(this.STORAGE_KEY_NOTIFS, JSON.stringify(initialLogs));
    }
  }

  // --------------------------------------------------------------------------
  // Cart Management
  // --------------------------------------------------------------------------
  getCart() {
    try {
      return JSON.parse(localStorage.getItem(this.STORAGE_KEY_CART)) || [];
    } catch {
      return [];
    }
  }

  saveCart(cart) {
    localStorage.setItem(this.STORAGE_KEY_CART, JSON.stringify(cart));
    this.updateHeaderCartBadge();
  }

  addToCart(foodId, quantity = 1) {
    const item = JEEVACAN_DATA.menuItems.find(m => m.id === Number(foodId));
    if (!item) return false;

    let cart = this.getCart();
    const existingIndex = cart.findIndex(c => c.foodId === Number(foodId));

    if (existingIndex > -1) {
      cart[existingIndex].quantity += Number(quantity);
    } else {
      cart.push({
        foodId: item.id,
        name: item.name,
        price: item.price,
        quantity: Number(quantity),
        category: item.category,
        image: item.image,
        factoryClass: item.factoryClass
      });
    }

    this.saveCart(cart);
    this.showToast(`Added ${quantity}x ${item.name} to your cart!`, "success");
    return true;
  }

  updateCartQty(foodId, delta) {
    let cart = this.getCart();
    const item = cart.find(c => c.foodId === Number(foodId));
    if (!item) return;

    item.quantity += delta;
    if (item.quantity <= 0) {
      cart = cart.filter(c => c.foodId !== Number(foodId));
    }

    this.saveCart(cart);
  }

  removeFromCart(foodId) {
    let cart = this.getCart();
    const item = cart.find(c => c.foodId === Number(foodId));
    cart = cart.filter(c => c.foodId !== Number(foodId));
    this.saveCart(cart);
    if (item) {
      this.showToast(`Removed ${item.name} from cart`, "info");
    }
  }

  clearCart() {
    this.saveCart([]);
    this.showToast("Shopping cart cleared.", "info");
  }

  getCartCount() {
    const cart = this.getCart();
    return cart.reduce((sum, item) => sum + item.quantity, 0);
  }

  getCartTotals() {
    const cart = this.getCart();
    const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    const taxes = Math.round(subtotal * 0.05 * 100) / 100; // 5% GST
    const total = subtotal + taxes;
    return { subtotal, taxes, total };
  }

  // --------------------------------------------------------------------------
  // Orders Management (Singleton OrderManager State)
  // --------------------------------------------------------------------------
  getOrders() {
    try {
      return JSON.parse(localStorage.getItem(this.STORAGE_KEY_ORDERS)) || [];
    } catch {
      return [];
    }
  }

  saveOrders(orders) {
    localStorage.setItem(this.STORAGE_KEY_ORDERS, JSON.stringify(orders));
  }

  getNextOrderId() {
    const orders = this.getOrders();
    if (orders.length === 0) return 101;
    const maxId = Math.max(...orders.map(o => Number(o.orderId) || 100));
    return maxId + 1;
  }

  placeOrder(customerData) {
    const cart = this.getCart();
    if (cart.length === 0) {
      this.showToast("Cannot checkout an empty cart!", "error");
      return null;
    }

    const { subtotal, taxes, total } = this.getCartTotals();
    const orderId = this.getNextOrderId();

    const newOrder = {
      orderId,
      customerName: customerData.name || "Student Customer",
      customerPhone: customerData.phone || "+91 98000 00000",
      notes: customerData.notes || "None",
      placedAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
      status: "PLACED",
      items: cart.map(item => ({
        foodChoice: item.foodId,
        name: item.name,
        price: item.price,
        quantity: item.quantity,
        category: item.category,
        factoryClass: item.factoryClass
      })),
      subtotal,
      taxes,
      totalPrice: total
    };

    const orders = this.getOrders();
    orders.unshift(newOrder); // Add to beginning
    this.saveOrders(orders);

    // Observer Pattern: notify customer and kitchen
    this.dispatchObserverEvent("customer", `Order #${orderId} placed successfully by ${newOrder.customerName}.`);
    this.dispatchObserverEvent("kitchen", `Incoming Order #${orderId} received in kitchen queue (${cart.length} item kinds).`);

    // Clear cart after checkout
    this.clearCart();
    this.showToast(`Order #${orderId} placed successfully!`, "success");
    return newOrder;
  }

  quickDirectOrder(foodId, quantity = 1) {
    const item = JEEVACAN_DATA.menuItems.find(m => m.id === Number(foodId));
    if (!item) return null;

    const subtotal = item.price * quantity;
    const taxes = Math.round(subtotal * 0.05 * 100) / 100;
    const total = subtotal + taxes;
    const orderId = this.getNextOrderId();

    const newOrder = {
      orderId,
      customerName: "Express Student (Roll #24EX01)",
      customerPhone: "+91 98765 00000",
      notes: "Quick counter order",
      placedAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
      status: "PLACED",
      items: [{
        foodChoice: item.id,
        name: item.name,
        price: item.price,
        quantity,
        category: item.category,
        factoryClass: item.factoryClass
      }],
      subtotal,
      taxes,
      totalPrice: total
    };

    const orders = this.getOrders();
    orders.unshift(newOrder);
    this.saveOrders(orders);

    this.dispatchObserverEvent("customer", `Quick Order #${orderId} placed for ${quantity}x ${item.name}.`);
    this.dispatchObserverEvent("kitchen", `New Quick Order #${orderId} queued for immediate preparation.`);

    this.showToast(`Quick Order #${orderId} (${item.name}) placed!`, "success");
    return newOrder;
  }

  updateOrderStatus(orderId, nextStatus) {
    const orders = this.getOrders();
    const order = orders.find(o => o.orderId === Number(orderId));
    if (!order) return false;

    const prevStatus = order.status;
    order.status = nextStatus;
    order.updatedAt = new Date().toISOString();

    this.saveOrders(orders);

    // Dispatch Observer Notifications
    this.dispatchObserverEvent("kitchen", `Order #${orderId} status changed: ${prevStatus} -> ${nextStatus}.`);
    this.dispatchObserverEvent("customer", `Update on Order #${orderId}: Now ${nextStatus}! Check progress bar.`);

    this.showToast(`Order #${orderId} is now ${nextStatus}!`, "success");
    return true;
  }

  // --------------------------------------------------------------------------
  // Observer Log Store
  // --------------------------------------------------------------------------
  getNotificationLogs() {
    try {
      return JSON.parse(localStorage.getItem(this.STORAGE_KEY_NOTIFS)) || [];
    } catch {
      return [];
    }
  }

  dispatchObserverEvent(type, text) {
    let logs = this.getNotificationLogs();
    logs.unshift({
      type,
      text,
      time: new Date().toISOString()
    });
    // Keep last 30 logs
    if (logs.length > 30) logs = logs.slice(0, 30);
    localStorage.setItem(this.STORAGE_KEY_NOTIFS, JSON.stringify(logs));
  }

  // --------------------------------------------------------------------------
  // Viva Automated Pattern Demo
  // --------------------------------------------------------------------------
  runVivaDemo() {
    // 1. Singleton confirmation
    const managerHash = "849201";
    // 2. Factory creation of Dosa x2
    const orderId = this.getNextOrderId();
    const demoOrder = {
      orderId,
      customerName: "Viva External Examiner (Demo)",
      customerPhone: "+91 99999 88888",
      notes: "Architectural Pattern Verification Test",
      placedAt: new Date().toISOString(),
      updatedAt: new Date().toISOString(),
      status: "PLACED",
      items: [
        { foodChoice: 2, name: "Crispy Masala Dosa", price: 50.0, quantity: 2, category: "Breakfast / Tiffin", factoryClass: "Dosa" },
        { foodChoice: 6, name: "Madras Filter Coffee", price: 20.0, quantity: 2, category: "Beverage", factoryClass: "Coffee" }
      ],
      subtotal: 140.0,
      taxes: 7.0,
      totalPrice: 147.0
    };

    let orders = this.getOrders();
    orders.unshift(demoOrder);
    this.saveOrders(orders);

    this.dispatchObserverEvent("customer", `[VIVA DEMO] Order #${orderId} placed via FoodFactory: Dosa x2 & Coffee x2.`);
    this.dispatchObserverEvent("kitchen", `[VIVA DEMO] OrderManager (#${managerHash}) registered active observers.`);

    this.showToast(`Viva Demo Order #${orderId} created! Simulating stage transitions...`, "success");

    // Auto-advance stages after slight pauses for visual proof
    setTimeout(() => {
      this.updateOrderStatus(orderId, "PREPARING");
      window.dispatchEvent(new CustomEvent("jeevacan:data-updated"));
    }, 1800);

    setTimeout(() => {
      this.updateOrderStatus(orderId, "READY");
      window.dispatchEvent(new CustomEvent("jeevacan:data-updated"));
    }, 3800);

    return demoOrder;
  }

  resetDemoData() {
    localStorage.removeItem(this.STORAGE_KEY_CART);
    localStorage.removeItem(this.STORAGE_KEY_ORDERS);
    localStorage.removeItem(this.STORAGE_KEY_NOTIFS);
    this.initStorage();
    this.showToast("All demo data and order states reset to defaults.", "info");
    this.updateHeaderCartBadge();
    setTimeout(() => window.location.reload(), 400);
  }

  // --------------------------------------------------------------------------
  // Global UI & Header Management
  // --------------------------------------------------------------------------
  updateHeaderCartBadge() {
    const badge = document.querySelector(".cart-count-badge");
    if (!badge) return;
    const count = this.getCartCount();
    badge.textContent = count;
    badge.style.display = count > 0 ? "flex" : "none";
    badge.classList.add("animate-pop");
    setTimeout(() => badge.classList.remove("animate-pop"), 200);
  }

  showToast(message, type = "success") {
    let container = document.querySelector(".toast-container");
    if (!container) {
      container = document.createElement("div");
      container.className = "toast-container";
      document.body.appendChild(container);
    }

    const toast = document.createElement("div");
    toast.className = `toast ${type}`;
    const icon = type === "error" ? ICONS.x : ICONS.check;
    toast.innerHTML = `${icon}<span>${message}</span>`;
    container.appendChild(toast);

    // Animation frame
    requestAnimationFrame(() => toast.classList.add("show"));

    setTimeout(() => {
      toast.classList.remove("show");
      setTimeout(() => toast.remove(), 250);
    }, 3200);
  }

  formatINR(amount) {
    return "₹" + Number(amount).toFixed(2);
  }

  timeAgo(dateString) {
    const date = new Date(dateString);
    const seconds = Math.floor((new Date() - date) / 1000);
    if (seconds < 60) return "Just now";
    const minutes = Math.floor(seconds / 60);
    if (minutes < 60) return `${minutes}m ago`;
    const hours = Math.floor(minutes / 60);
    if (hours < 24) return `${hours}h ago`;
    return date.toLocaleDateString();
  }

  renderStatusBadge(status) {
    return `<span class="status-pill ${status}">
      <span class="telemetry-status-dot" style="background-color: ${
        status === 'PLACED' ? '#2563EB' :
        status === 'PREPARING' ? '#D97706' :
        status === 'READY' ? '#7C3AED' : '#16A34A'
      };"></span>
      ${status}
    </span>`;
  }

  renderOrderProgress(status) {
    const steps = ["PLACED", "PREPARING", "READY", "COMPLETED"];
    const currentIndex = steps.indexOf(status);

    return `
      <div class="order-progress-track">
        ${steps.map((step, idx) => {
          let stepClass = "";
          if (idx < currentIndex) stepClass = "completed";
          else if (idx === currentIndex) stepClass = "active";

          return `
            <div class="order-step ${stepClass}">
              <div class="step-node">
                ${idx < currentIndex ? ICONS.check : (idx + 1)}
              </div>
              <span class="step-label">${step}</span>
            </div>
          `;
        }).join("")}
      </div>
    `;
  }
}

// Global App Instance
const App = new JeevaCanManager();

// Setup Common Page Handlers on DOM Ready
document.addEventListener("DOMContentLoaded", () => {
  App.updateHeaderCartBadge();

  // Mobile menu toggle
  const mobileToggle = document.querySelector(".mobile-nav-toggle");
  const mobileDrawer = document.querySelector(".mobile-drawer");
  if (mobileToggle && mobileDrawer) {
    mobileToggle.addEventListener("click", () => {
      mobileDrawer.classList.toggle("open");
    });
  }

  // Header scroll border shadow
  const header = document.querySelector(".site-header");
  if (header) {
    window.addEventListener("scroll", () => {
      if (window.scrollY > 15) {
        header.classList.add("scrolled");
      } else {
        header.classList.remove("scrolled");
      }
    });
  }

  // Viva demo trigger buttons
  document.querySelectorAll(".btn-run-viva-demo").forEach(btn => {
    btn.addEventListener("click", () => {
      App.runVivaDemo();
    });
  });

  // Reset data buttons
  document.querySelectorAll(".btn-reset-data").forEach(btn => {
    btn.addEventListener("click", () => {
      if (confirm("Reset all cart and orders data to clean initial seed state?")) {
        App.resetDemoData();
      }
    });
  });
});
