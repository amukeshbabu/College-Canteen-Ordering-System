package com.canteen.model;

import com.canteen.factory.FoodItem;

/**
 * Model class for managing items inside the shopping cart.
 */
public class CartItem {
    private final int foodChoice;
    private final FoodItem foodItem;
    private int quantity;

    public CartItem(int foodChoice, FoodItem foodItem, int quantity) {
        this.foodChoice = foodChoice;
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public int getFoodChoice() {
        return foodChoice;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return foodItem.getPrice() * quantity;
    }
}
