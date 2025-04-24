/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

import com.mycompany.bookshop.exceptions.BookNotFoundException;
import com.mycompany.bookshop.exceptions.InvalidInputException;
import com.mycompany.bookshop.exceptions.OutOfStockException;
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

    public Map<Integer, Integer> addItem(int bookId, int quantity, Books book) {
        if (book == null) {
            throw new BookNotFoundException("Book not Found");
        }
        
        int stockQuantity = book.getStockQuantity();
        
        if (quantity <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero");
        }
        
        int currentQuantity = items.getOrDefault(bookId, 0);
        int newTotalQuantity = currentQuantity + quantity;
        
        if (newTotalQuantity > stockQuantity) {
            throw new OutOfStockException("Not enough stock available. Only " + stockQuantity + " remaining.");
        }
        items.put(bookId, newTotalQuantity);
        book.setStockQuantity(stockQuantity - quantity);
        
        return items;
    }

    public Map<Integer, Integer> updateItem(int bookId, int newQuantity, Books book) {
        if (book == null) {
            throw new BookNotFoundException("Book not Found");
        }
        
        if (newQuantity < 0){
            throw new InvalidInputException("Invalid Quantity. Enter a positive value.");
        }       
        if (newQuantity == 0) {
            removeItem(bookId, book);
            return items;
        }        
        int stockQuantity = book.getStockQuantity();
        int currentInCart = items.getOrDefault(bookId, 0);    
        int difference = newQuantity - currentInCart;
        if (difference > 0 && difference > stockQuantity) {
            throw new OutOfStockException("Not enough stock available. Only " + stockQuantity + " remaining.");
        }
        book.setStockQuantity(stockQuantity - difference);
        items.put(bookId, newQuantity);
        return items;
    }
    
    public Map<Integer, Integer> removeItem(int bookId, Books book) {
        if (book == null) {
            throw new BookNotFoundException("Book not Found");
        }      
        int quantityInCart = items.get(bookId);
        if (quantityInCart > 0) {
            book.setStockQuantity(book.getStockQuantity() + quantityInCart);
            items.remove(bookId);
        }
        return items;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void clear(Customers customer) {
        items.clear();
    }
   
}