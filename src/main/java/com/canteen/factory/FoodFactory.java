package com.canteen.factory;

/**
 * =========================================================================
 * DESIGN PATTERN: FACTORY PATTERN
 * =========================================================================
 * Purpose: Encapsulates object creation logic and returns the appropriate FoodItem object.
 * 
 * Why it is used:
 * The client code (e.g., Main or OrderManager) does not need to know how concrete food
 * objects are instantiated using 'new Dosa()', 'new Idli()', etc.
 * Instead, client code requests a food item from FoodFactory by ID or string name,
 * and the factory encapsulates the instantiation logic and returns a FoodItem reference.
 * =========================================================================
 */
public class FoodFactory {

    /**
     * Factory method to create food item based on menu choice (1..6).
     * 
     * @param choice Numerical choice selected from the menu
     * @return Concrete object implementing FoodItem interface
     */
    public static FoodItem createFoodItem(int choice) {
        // Factory Pattern: Encapsulates object creation and returns the appropriate FoodItem object.
        switch (choice) {
            case 1:
                return new Idli();
            case 2:
                return new Dosa();
            case 3:
                return new FriedRice();
            case 4:
                return new VegMeals();
            case 5:
                return new Tea();
            case 6:
                return new Coffee();
            default:
                throw new IllegalArgumentException("Invalid food choice selected: " + choice);
        }
    }

    /**
     * Factory method to create food item based on string name.
     * 
     * @param foodName Name of the food (e.g., "DOSA", "IDLI", "TEA")
     * @return Concrete object implementing FoodItem interface
     */
    public static FoodItem createFoodItemByName(String foodName) {
        if (foodName == null) {
            throw new IllegalArgumentException("Food name cannot be null.");
        }
        String normalized = foodName.trim().toUpperCase();
        switch (normalized) {
            case "IDLI":
                return new Idli();
            case "DOSA":
                return new Dosa();
            case "FRIED RICE":
            case "FRIEDRICE":
                return new FriedRice();
            case "VEG MEALS":
            case "VEGMEALS":
                return new VegMeals();
            case "TEA":
                return new Tea();
            case "COFFEE":
                return new Coffee();
            default:
                throw new IllegalArgumentException("Unknown food name: " + foodName);
        }
    }
}
