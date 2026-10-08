package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN (Concrete Product)
 * 
 * Represents the concrete food item: Fried Rice.
 */
public class FriedRice implements FoodItem {
    @Override
    public String getName() {
        return "Fried Rice";
    }

    @Override
    public double getPrice() {
        return 80.0;
    }

    @Override
    public String getCategory() {
        return "Main Course";
    }
}
