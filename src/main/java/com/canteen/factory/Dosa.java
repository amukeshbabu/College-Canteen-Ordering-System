package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN (Concrete Product)
 * 
 * Represents the concrete food item: Dosa.
 */
public class Dosa implements FoodItem {
    @Override
    public String getName() {
        return "Dosa";
    }

    @Override
    public double getPrice() {
        return 50.0;
    }

    @Override
    public String getCategory() {
        return "Breakfast / Tiffin";
    }
}
