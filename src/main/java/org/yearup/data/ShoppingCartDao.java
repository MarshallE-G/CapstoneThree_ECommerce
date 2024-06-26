package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    
    ShoppingCart add(ShoppingCartItem shoppingCartItem);
    
    void update(int productId, ShoppingCart shoppingCart);
    
    void delete(int productId);
}
