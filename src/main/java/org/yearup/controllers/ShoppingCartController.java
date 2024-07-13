package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("cart")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")
public class ShoppingCartController
{
    private ShoppingCartDao shoppingCartDao;
    private UserDao         userDao;
    private ProductDao      productDao;
    
    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao)
    {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }
    
    @GetMapping("")
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            // get the currently logged-in username
            String userName = principal.getName();
            // find database user by userId
            User user   = userDao.getByUserName(userName);
            int  userId = user.getId();
            
            // use the shoppingCartDao to get all items in the cart and return the cart
            var shoppingCart = shoppingCartDao.getByUserId(userId);
            
            if(shoppingCart == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            
            return shoppingCart;
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    
    @PostMapping("/products/{productId}")
    public ShoppingCartItem addItem(@PathVariable int productId, Principal principal)
    {
        try
        {
            String userName = principal.getName();
            User   user     = userDao.getByUserName(userName);
            int    userId   = user.getId();
            
            var shoppingCart = shoppingCartDao.getByUserId(userId);
            
            if(!shoppingCart.contains(productId))
            {
                Product          product          = productDao.getById(productId);
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                
                shoppingCartItem.setProduct(product);
                return shoppingCartDao.add(userId, shoppingCartItem);
            } else
            {
                ShoppingCartItem shoppingCartItem = shoppingCart.get(productId);
                
                shoppingCartDao.update(userId, shoppingCartItem);
                
                return shoppingCartItem;
            }
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    
    @PutMapping("/products/{productId}")
    public ShoppingCart updateCartItem(@PathVariable int productId,
                                       @RequestBody ShoppingCartItem shoppingCartItem,
                                       Principal principal)
    {
        try
        {
            String userName = principal.getName();
            User   user     = userDao.getByUserName(userName);
            int    userId   = user.getId();
            
            var shoppingCart = shoppingCartDao.getByUserId(userId);
            
            if(shoppingCart.contains(productId))
            {
                shoppingCartDao.update(userId, shoppingCartItem);
                return shoppingCart;
            }
            
            return shoppingCart;
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
    
    @DeleteMapping()
    public void deleteCart(Principal principal)
    {
        try
        {
            String userName = principal.getName();
            User   user     = userDao.getByUserName(userName);
            int    userId   = user.getId();
            
            var shoppingCart = shoppingCartDao.getByUserId(userId);
            
            if(shoppingCart == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            
            shoppingCartDao.delete(userId);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
