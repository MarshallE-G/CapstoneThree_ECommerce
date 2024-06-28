package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.OrderLineItemDao;
import org.yearup.models.OrderLineItem;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlOrderLineItemDao extends MySqlDaoBase implements OrderLineItemDao
{
    public MySqlOrderLineItemDao(DataSource dataSource)
    {
        super(dataSource);
    }
    
    @Override
    public OrderLineItem getById(int orderLineItemId)
    {
        String sql = "SELECT * FROM order_line_items WHERE order_line_item_id = ?";
        
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1, orderLineItemId);
    
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
    public OrderLineItem create(int orderId, ShoppingCartItem shoppingCartItem)
    {
        String sql = "INSERT INTO order_line_items(order_id, product_id, sales_price, quantity, discount) " +
                " VALUES (?, ?, ?, ?, ?);";
    
        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setOrderId(orderId);
        lineItem.setProductId(shoppingCartItem.getProductId());
        lineItem.setSalesPrice(shoppingCartItem.getProduct().getPrice());
        lineItem.setQuantity(shoppingCartItem.getQuantity());
        lineItem.setDiscountPercent(shoppingCartItem.getDiscountPercent());
        
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        )
        {
            statement.setInt(1, lineItem.getOrderId());
            statement.setInt(2, lineItem.getProductId());
            statement.setBigDecimal(3, lineItem.getSalesPrice());
            statement.setInt(4, lineItem.getQuantity());
            statement.setBigDecimal(5, lineItem.getDiscountPercent());
            
            int rowsAffected = statement.executeUpdate();
            
            if(rowsAffected > 0)
            {
                try(ResultSet generatedKeys = statement.getGeneratedKeys();)
                {
                    if(generatedKeys.next())
                    {
                        int orderLineItemId = generatedKeys.getInt(1);
                        
                        return getById(orderLineItemId);
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
    
    private OrderLineItem mapRow(ResultSet row) throws SQLException
    {
        int        orderLineItemId = row.getInt("order_line_item_id");
        int        orderId         = row.getInt("order_id");
        int        productId       = row.getInt("product_id");
        BigDecimal salesPrice      = row.getBigDecimal("sales_price");
        int        quantity        = row.getInt("quantity");
        BigDecimal discountPercent = row.getBigDecimal("discount");
        
        return new OrderLineItem(orderLineItemId, orderId, productId, salesPrice, quantity, discountPercent);
    }
}
