package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN (Concrete Product)
 * 
 * Represents the concrete food item: Veg Meals.
 */
public class VegMeals implements FoodItem {
    @Override
    public String getName() {
        return "Veg Meals";
    }

    @Override
    public double getPrice() {
        return 100.0;
    }

    @Override
    public String getCategory() {
        return "Main Course";
    }
}
