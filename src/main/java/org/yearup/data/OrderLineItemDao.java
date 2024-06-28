package org.yearup.data;

import org.yearup.models.OrderLineItem;
import org.yearup.models.ShoppingCartItem;

public interface OrderLineItemDao
{
    OrderLineItem getById(int orderLineItemId);
    
    OrderLineItem create(int orderId, ShoppingCartItem shoppingCartItem);
}
