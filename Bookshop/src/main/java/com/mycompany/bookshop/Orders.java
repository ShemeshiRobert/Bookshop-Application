/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shemeshi Robert
 */
public class Orders {
    private int id;
    private int customerId;
    private Date orderDate;
    private Map<Integer, Integer> items;
    private double totalAmount;
    private String status; 
    
    public Orders() {
        this.items = new HashMap<>();
        this.orderDate = new Date();
        this.status = "PENDING";
    }
    
    public Orders(int id, int customerId, Map<Integer, Integer> items, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = new Date();
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = "PENDING";
    }
    
    public static Orders fromCart(int orderId, int customerId, Carts cart, List<Books> books) {
        Orders order = new Orders();
        order.setId(orderId);
        order.setCustomerId(customerId);
        
        Map<Integer, Integer> cartItems = cart.getItems();
        order.setItems(new HashMap<>(cartItems));
        
        double total = 0.0;
        for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
            int bookId = entry.getKey();
            int quantity = entry.getValue();

            for (Books book : books) {
                if (book.getId() == bookId) {
                    total += book.getPrice() * quantity;
                    break;
                }
            }
        }
        
        order.setTotalAmount(total);
        return order;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public Map<Integer, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
