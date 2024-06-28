package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.*;
import org.yearup.models.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrdersController
{
    private OrderDao         orderDao;
    private OrderLineItemDao orderLineItemDao;
    private UserDao          userDao;
    private ProfileDao       profileDao;
    private ShoppingCartDao  shoppingCartDao;
    
    @Autowired
    public OrdersController(OrderDao orderDao, OrderLineItemDao orderLineItemDao, UserDao userDao, ProfileDao profileDao, ShoppingCartDao shoppingCartDao)
    {
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.shoppingCartDao = shoppingCartDao;
    }
    
    @PostMapping
    public Order createOrder(Principal principal)
    {
        try
        {
            String userName = principal.getName();
            User   user     = userDao.getByUserName(userName);
            int    userId   = user.getId();
            
            Profile profile = profileDao.getByUserId(userId);
            
            ShoppingCart shoppingCart = shoppingCartDao.getByUserId(userId);
            
            Order order = orderDao.create(profile, shoppingCart);
            int orderId = order.getOrderId();
            
            for(Map.Entry<Integer, ShoppingCartItem> set :
                    shoppingCart.getItems().entrySet())
            {
                ShoppingCartItem shoppingCartItem = set.getValue();
                
                orderLineItemDao.create(orderId, shoppingCartItem);
            }
            
            return order;
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
