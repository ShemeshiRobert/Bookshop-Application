/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

import com.mycompany.bookshop.exceptions.BookNotFoundException;
import com.mycompany.bookshop.resources.BookResource;
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
    
    public Orders() {

    }
    
    public Orders(int id, int customerId, Map<Integer, Integer> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }
    
    public static Orders fromCart(int orderId, int customerId, Carts cart) {
        Orders order = new Orders();
        order.setId(orderId);
        order.setCustomerId(customerId);
        
        double total = order.calculateTotalPrice(cart);       
        order.setTotalAmount(total);
        order.items = new HashMap<>(cart.getItems());
        return order;
    }
    public double calculateTotalPrice(Carts cart){
        Map<Integer, Integer> items = cart.getItems();
        double totalPrice = 0.0;
        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
        int bookId = entry.getKey();
        int quantity = entry.getValue();

        Books book = BookResource.getBookById(bookId);
        if (book != null) {
            double price = book.getPrice();
            totalPrice += price * quantity;
        } else {
            throw new BookNotFoundException("Book not found for ID: " + bookId);
        }
        }
        return totalPrice;
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
}
