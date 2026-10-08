package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN (Concrete Product)
 * 
 * Represents the concrete food item: Idli.
 */
public class Idli implements FoodItem {
    @Override
    public String getName() {
        return "Idli";
    }

    @Override
    public double getPrice() {
        return 30.0;
    }

    @Override
    public String getCategory() {
        return "Breakfast / Tiffin";
    }
}
