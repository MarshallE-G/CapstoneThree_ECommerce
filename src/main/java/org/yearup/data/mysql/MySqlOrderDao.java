package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.OrderDao;
import org.yearup.models.Order;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MySqlOrderDao extends MySqlDaoBase implements OrderDao
{
    public MySqlOrderDao(DataSource dataSource)
    {
        super(dataSource);
    }
    
    @Override
    public Order getById(int orderId)
    {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1, orderId);
            
            try(ResultSet row = statement.executeQuery();)
            {
                if(row.next())
                {
                    return mapRow(row);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    @Override
    public Order create(Profile profile, ShoppingCart shoppingCart)
    {
        String sql = "INSERT INTO orders(user_id, date, address, city, state, zip) " +
                " VALUES (?, ?, ?, ?, ?, ?);";
        
        LocalDateTime     dateTime          = LocalDateTime.now();
        DateTimeFormatter formatter         = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String            formattedDateTime = dateTime.format(formatter);
        
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        )
        {
            statement.setInt(1, profile.getUserId());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(formattedDateTime)); // A TimeStamp is like... "DateTime"
            statement.setString(3, profile.getAddress());
            statement.setString(4, profile.getCity());
            statement.setString(5, profile.getState());
            statement.setString(6, profile.getZip());
            
            int rowsAffected = statement.executeUpdate();
            
            if(rowsAffected > 0)
            {
                try(ResultSet generatedKeys = statement.getGeneratedKeys();)
                {
                    if(generatedKeys.next())
                    {
                        int orderId = generatedKeys.getInt(1);
                        
                        return getById(orderId);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    private Order mapRow(ResultSet row) throws SQLException
    {
        int        orderId        = row.getInt("order_id");
        int        userId         = row.getInt("user_id");
        String     date           = row.getTimestamp("date").toString();
        String     address        = row.getString("address");
        String     city           = row.getString("city");
        String     state          = row.getString("state");
        String     zip            = row.getString("zip");
        BigDecimal shippingAmount = row.getBigDecimal("shipping_amount");
        
        return new Order(orderId, userId, date, address, city, state, zip, shippingAmount);
    }
}
