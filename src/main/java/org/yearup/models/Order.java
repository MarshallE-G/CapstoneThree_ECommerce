package org.yearup.models;

import java.math.BigDecimal;

public class Order
{
    private int        orderId; // auto-increments
    private int        userId; // User (must use Principal object)
    private String     date;
    private String     address; // Profile
    private String     city; // Profile
    private String     state; // Profile
    private String     zip; // Profile
    private BigDecimal shippingAmount = BigDecimal.ZERO; // default = 0
    
    public Order()
    {
    }
    
    public Order(int orderId, int userId, String date, String address, String city, String state, String zip)
    {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    public Order(int orderId, int userId, String date, String address, String city, String state, String zip, BigDecimal shippingAmount)
    {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shippingAmount = shippingAmount;
    }
    
    public int getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }
    
    public int getUserId()
    {
        return userId;
    }
    
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    public String getZip()
    {
        return zip;
    }
    
    public void setZip(String zip)
    {
        this.zip = zip;
    }
    
    public BigDecimal getShippingAmount()
    {
        return shippingAmount;
    }
    
    public void setShippingAmount(BigDecimal shippingAmount)
    {
        this.shippingAmount = shippingAmount;
    }
}
