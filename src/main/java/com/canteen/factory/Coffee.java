package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN (Concrete Product)
 * 
 * Represents the concrete food item: Coffee.
 */
public class Coffee implements FoodItem {
    @Override
    public String getName() {
        return "Coffee";
    }

    @Override
    public double getPrice() {
        return 20.0;
    }

    @Override
    public String getCategory() {
        return "Beverage";
    }
}
