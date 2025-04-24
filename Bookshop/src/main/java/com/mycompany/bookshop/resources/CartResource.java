/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.resources;

import com.mycompany.bookshop.Books;
import com.mycompany.bookshop.Customers;
import com.mycompany.bookshop.Carts;
import com.mycompany.bookshop.exceptions.BookNotFoundException;
import com.mycompany.bookshop.exceptions.CartNotFoundException;
import com.mycompany.bookshop.exceptions.CustomerNotFoundException;
import com.mycompany.bookshop.exceptions.InvalidInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Shemeshi Robert
 */

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private static final Map<Integer, Carts> carts = new HashMap<>(); //customerId, cart   
    
    private Books findBookById(int bookId) {
        Books book = BookResource.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        return book;
    }

    private boolean customerExists(int customerId) {
        Customers customer = CustomerResource.getCustomerById(customerId);
        if (customer != null && customerId > 0)
            return true;
        else
            return false;
    }
    
    private Carts getOrCreateCart(int customerId) {
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        
        Carts cart = carts.get(customerId);
        if (cart == null) {
            cart = new Carts(customerId);
            carts.put(customerId, cart);
        }
        return cart;
    }
    
    @POST
    @Path("/items")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItemToCart(@PathParam("customerId") int customerId, Map<String, Integer> items) {
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        Carts cart = getOrCreateCart(customerId);
        Integer bookId = items.get("bookId");
        Integer quantity = items.get("quantity");       
        Books book = findBookById(bookId);
        cart.addItem(bookId, quantity, book);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Carts getCart(@PathParam("customerId") int customerId) {
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        Carts cart = carts.get(customerId);
        if (cart == null) {
            cart = new Carts(customerId);
            carts.put(customerId, cart);
        }
        
        return cart;
    }
    
    @PUT
    @Path("/items/{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateItemQuantity(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId, int quantity){       
        if (customerId <= 0 ) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        Carts cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found.");
        }
        
        Books book = findBookById(bookId);
        cart.updateItem(bookId, quantity, book);
    }
    
    @DELETE
    @Path("/items/{bookId}")
    public Carts removeItem(@PathParam("customerId") int customerId,@PathParam("bookId") int bookId) {       
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }        
        Carts cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found.");
        }       
        Books book = findBookById(bookId);       
        cart.removeItem(bookId, book);        
        return cart;
    }
}