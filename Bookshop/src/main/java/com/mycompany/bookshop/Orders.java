/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shemeshi Robert
 */
public class Orders {
    private Customers customer;
    private Map<Books, Integer> purchasedOrder;
    
    public Orders(){
    
    }
    
     public Orders(Carts cart) {
        this.customer = cart.getCustomer();
        this.purchasedOrder = new HashMap<>(cart.getItems());
    }
    public Map<Books, Integer> getOrders() {
        return purchasedOrder;
    }
    public Customers getCustomer() {
        return customer;
    }
    
}
