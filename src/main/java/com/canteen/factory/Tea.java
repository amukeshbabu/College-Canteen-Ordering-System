package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN (Concrete Product)
 * 
 * Represents the concrete food item: Tea.
 */
public class Tea implements FoodItem {
    @Override
    public String getName() {
        return "Tea";
    }

    @Override
    public double getPrice() {
        return 15.0;
    }

    @Override
    public String getCategory() {
        return "Beverage";
    }
}
