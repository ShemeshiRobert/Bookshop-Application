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
    private int customerId;
    private Map<Integer, Integer> items; // Map<BookId, Quantity>

    public Carts(int customerId) {
        this.customerId = customerId;
        this.items = new HashMap<>();
    }
    
    public Carts() {
        this.items = new HashMap<>();
    }

    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    // Add a book to the cart
    public void addItem(int bookId, int quantity, Books book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        int stockQuantity = book.getStockQuantity();
        
        // Check if adding this quantity would exceed available stock
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        
        int currentQuantity = items.getOrDefault(bookId, 0);
        int newTotalQuantity = currentQuantity + quantity;
        
        if (newTotalQuantity > stockQuantity) {
            throw new OutOfStockException("Not enough stock available. Only " + stockQuantity + " remaining.");
        }
        
        // Update cart and reduce book stock
        items.put(bookId, newTotalQuantity);
        book.setStockQuantity(stockQuantity - quantity);
    }

    // Update the quantity of a book in the cart
    public void updateItem(int bookId, int newQuantity, Books book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        if (newQuantity <= 0) {
            removeItem(bookId, book);
            return;
        }
        
        int stockQuantity = book.getStockQuantity();
        int currentInCart = items.getOrDefault(bookId, 0);
        
        // Calculate how many more books we need (could be negative if reducing quantity)
        int difference = newQuantity - currentInCart;
        
        // If increasing quantity, check if enough stock available
        if (difference > 0 && difference > stockQuantity) {
            throw new OutOfStockException("Not enough stock available. Only " + stockQuantity + " remaining.");
        }
        
        // Update book stock
        book.setStockQuantity(stockQuantity - difference);
        
        // Update cart
        items.put(bookId, newQuantity);
    }

    // Remove a book from the cart
    public void removeItem(int bookId, Books book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        
        Integer quantityInCart = items.get(bookId);
        if (quantityInCart != null && quantityInCart > 0) {
            // Return books to inventory
            book.setStockQuantity(book.getStockQuantity() + quantityInCart);
            
            // Remove from cart
            items.remove(bookId);
        }
    }
    
    // Check if the cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // Clear all items from the cart
    public void clear() {
        items.clear();
    }
    
    // Custom exception for out of stock scenarios
    public static class OutOfStockException extends RuntimeException {
        public OutOfStockException(String message) {
            super(message);
        }
    }
}