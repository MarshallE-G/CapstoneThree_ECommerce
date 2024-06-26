package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao
{
    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource)
    {
        super(dataSource);
    }
    
    @Override
    public ShoppingCart getByUserId(int userId)
    {
        // Get shopping cart associated with a specific user (id)
            // display all the items in the shopping cart (list)
                // display the quantity <---- getter
                    // if m
                // display the display percentage (%) <---- getter
                // display the line total (item price * quantity) <---- helper method
        // display the total amount <---- helper method
        
        
        String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
        
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1, userId);
            
            try (ResultSet row = statement.executeQuery()) {
                while (row.next()) {
                
                
                
                
                
                
                
                
                
                
                
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public ShoppingCart add(ShoppingCartItem shoppingCartItem)
    {
        return null;
    }
    
    @Override
    public void update(int productId, ShoppingCart shoppingCart)
    {
    
    }
    
    @Override
    public void delete(int productId)
    {
    
    }
}
