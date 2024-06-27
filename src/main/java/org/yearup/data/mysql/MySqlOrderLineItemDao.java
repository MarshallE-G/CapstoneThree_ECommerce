package org.yearup.data.mysql;

import org.yearup.data.OrderLineItemDao;
import org.yearup.models.OrderLineItem;

import javax.sql.DataSource;

public class MySqlOrderLineItemDao extends MySqlDaoBase implements OrderLineItemDao
{
    public MySqlOrderLineItemDao(DataSource dataSource)
    {
        super(dataSource);
    }
    
    @Override
    public OrderLineItem create(int orderId, OrderLineItem lineItem)
    {
        return null;
    }
}
