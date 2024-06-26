package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String sql = "";
        
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
        
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
