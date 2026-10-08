package com.canteen.factory;

/**
 * Design Pattern: FACTORY PATTERN
 * 
 * FoodItem serves as the common interface for all food products created by FoodFactory.
 * It encapsulates common food properties like name, price, and category.
 */
public interface FoodItem {
    String getName();
    double getPrice();
    String getCategory();
}
