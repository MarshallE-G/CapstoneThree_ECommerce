package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.*;
import org.yearup.models.Order;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrdersController
{
    private OrderDao         orderDao;
    private OrderLineItemDao orderLineItemDao;
    private UserDao          userDao;
    private ProfileDao       profileDao;
    private ShoppingCartDao shoppingCartDao;
    
    @Autowired
    public OrdersController(OrderDao orderDao, OrderLineItemDao orderLineItemDao, UserDao userDao, ProfileDao profileDao, ShoppingCartDao shoppingCartDao)
    {
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.shoppingCartDao = shoppingCartDao;
    }
    
    @GetMapping("")
    public Order createOrder(Principal principal)
    {
        try
        {
            String userName = principal.getName();
            User   user     = userDao.getByUserName(userName);
            int    userId   = user.getId();
    
            var profile = profileDao.getByUserId(userId);
            
            var shoppingCart = shoppingCartDao.getByUserId(userId);
            
            
            
            return orderDao.create(profile, shoppingCart);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
