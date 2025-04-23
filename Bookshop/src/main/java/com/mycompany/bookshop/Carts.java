/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shemeshi Robert
 */
public class Carts {
    private Customers customer;
    private Map<Books, Integer> items;

    public Carts(Customers customer) {
        this.customer = customer;
        this.items = new HashMap<>();
    }

    public void addBook(Books book, int quantity) {
        items.put(book, items.getOrDefault(book, 0) + quantity);
    }

    public void removeBook(Books book) {
        items.remove(book);
    }

    public void updateQuantity(Books book, int quantity) {
        if (quantity > 0){
            items.put(book, quantity);
        } else {
            System.out.println("Invalid quantity!");
        }
    }

    public Map<Books, Integer> getItems() {
        return items;
    }

    public Customers getCustomer() {
        return customer;
    }
    
}
