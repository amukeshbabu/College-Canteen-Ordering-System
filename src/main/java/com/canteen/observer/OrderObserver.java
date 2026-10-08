package com.canteen.observer;

import com.canteen.model.Order;
import com.canteen.model.OrderStatus;

/**
 * =========================================================================
 * DESIGN PATTERN: OBSERVER PATTERN (Observer Interface)
 * =========================================================================
 * Purpose: Declares the update interface for objects that should be notified 
 * of changes in a Subject (Order).
 * =========================================================================
 */
public interface OrderObserver {
    /**
     * Method invoked by the Subject (Order) when order status changes.
     * 
     * @param order The order whose status has changed
     * @param previousStatus The previous status before update
     */
    void update(Order order, OrderStatus previousStatus);
}
