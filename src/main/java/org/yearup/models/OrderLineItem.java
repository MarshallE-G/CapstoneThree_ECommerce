package org.yearup.models;

import java.math.BigDecimal;

public class OrderLineItem
{
    private int        orderLineItemId; // auto-increments
    private int        orderId; // Order
    private int        productId; // shoppingCartItem.getProductId()
    private BigDecimal salesPrice; // shoppingCartItem.getProduct().getPrice()
    private int        quantity; // shoppingCartItem.getQuantity
    private BigDecimal discountPercent; // shoppingCartItem.getDiscountPercent
    
    public OrderLineItem()
    {
    }
    
    public OrderLineItem(int orderLineItemId, int orderId, int productId, BigDecimal salesPrice, int quantity, BigDecimal discountPercent)
    {
        this.orderLineItemId = orderLineItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.discountPercent = discountPercent;
    }
    
    public int getOrderLineItemId()
    {
        return orderLineItemId;
    }
    
    public void setOrderLineItemId(int orderLineItemId)
    {
        this.orderLineItemId = orderLineItemId;
    }
    
    public int getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }
    
    public int getProductId()
    {
        return productId;
    }
    
    public void setProductId(int productId)
    {
        this.productId = productId;
    }
    
    public BigDecimal getSalesPrice()
    {
        return salesPrice;
    }
    
    public void setSalesPrice(BigDecimal salesPrice)
    {
        this.salesPrice = salesPrice;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public BigDecimal getDiscountPercent()
    {
        return discountPercent;
    }
    
    public void setDiscountPercent(BigDecimal discountPercent)
    {
        this.discountPercent = discountPercent;
    }
}
