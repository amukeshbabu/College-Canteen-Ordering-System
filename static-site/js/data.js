/**
 * JeevaCan Data Layer & Configuration
 * Provides static catalog data, design pattern metadata, and initial seed state.
 */

const JEEVACAN_DATA = {
  // Menu items based on original Java FoodFactory implementation
  menuItems: [
    {
      id: 1,
      name: "Steamed Idli",
      category: "Breakfast / Tiffin",
      price: 30.0,
      description: "Soft, fluffy steamed rice & lentil cakes served with freshly ground coconut chutney and piping hot sambar.",
      image: "assets/images/idli.jpg",
      isVeg: true,
      prepTime: "5-8 mins",
      rating: 4.8,
      factoryClass: "Idli",
      patternCategory: "Tiffin"
    },
    {
      id: 2,
      name: "Crispy Masala Dosa",
      category: "Breakfast / Tiffin",
      price: 50.0,
      description: "Golden, buttery thin fermented crepe filled with fragrant spiced potato masala, served with 2 signature chutneys.",
      image: "assets/images/dosa.jpg",
      isVeg: true,
      prepTime: "8-12 mins",
      rating: 4.9,
      factoryClass: "Dosa",
      patternCategory: "Tiffin"
    },
    {
      id: 3,
      name: "Veg Fried Rice",
      category: "Main Course",
      price: 80.0,
      description: "Aromatic wok-tossed basmati rice infused with crunchy seasonal vegetables, spring onions, ginger, and light soy seasoning.",
      image: "assets/images/fried_rice.jpg",
      isVeg: true,
      prepTime: "12-15 mins",
      rating: 4.7,
      factoryClass: "FriedRice",
      patternCategory: "Main Course"
    },
    {
      id: 4,
      name: "South Indian Veg Meals",
      category: "Main Course",
      price: 100.0,
      description: "Grand traditional thali featuring steamed rice, dal, sambar, rasam, kootu, poriyal, appalam, curd, and mango pickle.",
      image: "assets/images/veg_meals.jpg",
      isVeg: true,
      prepTime: "10-15 mins",
      rating: 4.9,
      factoryClass: "VegMeals",
      patternCategory: "Main Course"
    },
    {
      id: 5,
      name: "Special Filter Tea",
      category: "Beverage",
      price: 15.0,
      description: "Freshly brewed high-grown Assam tea leaves simmered with crushed green cardamom, ginger, and rich creamy milk.",
      image: "assets/images/tea.jpg",
      isVeg: true,
      prepTime: "3-5 mins",
      rating: 4.6,
      factoryClass: "Tea",
      patternCategory: "Beverage"
    },
    {
      id: 6,
      name: "Madras Filter Coffee",
      category: "Beverage",
      price: 20.0,
      description: "Authentic South Indian chicory-blended filter drip decoction frothed with fresh whole milk in traditional brass davarah.",
      image: "assets/images/coffee.jpg",
      isVeg: true,
      prepTime: "3-5 mins",
      rating: 4.9,
      factoryClass: "Coffee",
      patternCategory: "Beverage"
    },
    {
      id: 7,
      name: "Golden Samosa Combo",
      category: "Quick Bites",
      price: 25.0,
      description: "Twin flaky, crispy pastries stuffed with spiced potatoes and tender green peas, accompanied by sweet tamarind & mint relish.",
      image: "assets/images/samosa.jpg",
      isVeg: true,
      prepTime: "5 mins",
      rating: 4.7,
      factoryClass: "Samosa",
      patternCategory: "Quick Bites"
    },
    {
      id: 8,
      name: "Paneer Butter Masala Bowl",
      category: "Main Course",
      price: 110.0,
      description: "Tender cottage cheese cubes simmered in a velvety makhani gravy of vine tomatoes, cashew cream, and aromatic kasuri methi with jeera rice.",
      image: "assets/images/paneer.jpg",
      isVeg: true,
      prepTime: "12-16 mins",
      rating: 4.8,
      factoryClass: "PaneerMeals",
      patternCategory: "Main Course"
    }
  ],

  // Seed orders matching original OrderStatus flow: PLACED -> PREPARING -> READY -> COMPLETED
  seedOrders: [
    {
      orderId: 101,
      customerName: "Rahul Sharma (Roll #21CS042)",
      customerPhone: "+91 98765 43210",
      notes: "Less spicy please",
      placedAt: new Date(Date.now() - 28 * 60000).toISOString(),
      updatedAt: new Date(Date.now() - 5 * 60000).toISOString(),
      status: "READY",
      items: [
        { foodChoice: 2, name: "Crispy Masala Dosa", price: 50.0, quantity: 2, category: "Breakfast / Tiffin" },
        { foodChoice: 6, name: "Madras Filter Coffee", price: 20.0, quantity: 2, category: "Beverage" }
      ],
      subtotal: 140.0,
      taxes: 7.0,
      totalPrice: 147.0
    },
    {
      orderId: 102,
      customerName: "Priya Nair (Roll #22EC018)",
      customerPhone: "+91 98451 22334",
      notes: "Extra chutney",
      placedAt: new Date(Date.now() - 14 * 60000).toISOString(),
      updatedAt: new Date(Date.now() - 8 * 60000).toISOString(),
      status: "PREPARING",
      items: [
        { foodChoice: 3, name: "Veg Fried Rice", price: 80.0, quantity: 1, category: "Main Course" }
      ],
      subtotal: 80.0,
      taxes: 4.0,
      totalPrice: 84.0
    },
    {
      orderId: 103,
      customerName: "Ananya Iyer (Roll #20IT088)",
      customerPhone: "+91 97654 32109",
      notes: "Quick pickup between classes",
      placedAt: new Date(Date.now() - 4 * 60000).toISOString(),
      updatedAt: new Date(Date.now() - 4 * 60000).toISOString(),
      status: "PLACED",
      items: [
        { foodChoice: 1, name: "Steamed Idli", price: 30.0, quantity: 1, category: "Breakfast / Tiffin" },
        { foodChoice: 5, name: "Special Filter Tea", price: 15.0, quantity: 1, category: "Beverage" }
      ],
      subtotal: 45.0,
      taxes: 2.25,
      totalPrice: 47.25
    },
    {
      orderId: 104,
      customerName: "Aditya Verma (Roll #21ME035)",
      customerPhone: "+91 91234 56789",
      notes: "Packed lunch",
      placedAt: new Date(Date.now() - 75 * 60000).toISOString(),
      updatedAt: new Date(Date.now() - 40 * 60000).toISOString(),
      status: "COMPLETED",
      items: [
        { foodChoice: 4, name: "South Indian Veg Meals", price: 100.0, quantity: 2, category: "Main Course" }
      ],
      subtotal: 200.0,
      taxes: 10.0,
      totalPrice: 210.0
    },
    {
      orderId: 105,
      customerName: "Sneha Reddy (Roll #23CS012)",
      customerPhone: "+91 99887 76655",
      notes: "Hot beverage",
      placedAt: new Date(Date.now() - 110 * 60000).toISOString(),
      updatedAt: new Date(Date.now() - 90 * 60000).toISOString(),
      status: "COMPLETED",
      items: [
        { foodChoice: 7, name: "Golden Samosa Combo", price: 25.0, quantity: 2, category: "Quick Bites" },
        { foodChoice: 5, name: "Special Filter Tea", price: 15.0, quantity: 2, category: "Beverage" }
      ],
      subtotal: 80.0,
      taxes: 4.0,
      totalPrice: 84.0
    }
  ],

  // Design pattern educational metadata
  patterns: {
    singleton: {
      name: "Singleton Pattern",
      target: "OrderManager (Instance ID: #849201)",
      description: "Guarantees a single central instance of the order manager throughout the application lifecycle to avoid duplicate order IDs or split kitchen state."
    },
    factory: {
      name: "Factory Pattern",
      target: "FoodFactory.createFoodItem()",
      description: "Encapsulates object instantiation logic. The client requests items by ID/code without knowing internal class construction."
    },
    observer: {
      name: "Observer Pattern",
      target: "CustomerObserver & KitchenObserver",
      description: "Decouples order state transitions. Any change (PLACED → PREPARING → READY → COMPLETED) automatically notifies active listeners."
    }
  }
};
