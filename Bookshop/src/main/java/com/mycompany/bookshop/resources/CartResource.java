/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.resources;

import com.mycompany.bookshop.Books;
import com.mycompany.bookshop.Customers;
import com.mycompany.bookshop.Carts;
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
    private static final Map<Integer, Carts> carts = new HashMap<>();
    
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
    
    // Get or create a cart for a customer
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
    public Carts addItemToCart(@PathParam("customerId") int customerId, Map<String, Integer> payload) {
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        
        Integer bookId = payload.get("bookId");
        Integer quantity = payload.get("quantity");
        
        if (bookId == null || quantity == null) {
            throw new BadRequestException("Both bookId and quantity are required");
        }
        
        if (bookId <= 0) {
            throw new BadRequestException("Invalid book ID: " + bookId);
        }
        
        if (quantity <= 0) {
            throw new BadRequestException("Quantity must be greater than zero");
        }
        
        Books book = findBookById(bookId);
        
        Carts cart = getOrCreateCart(customerId);
        
        try {
            cart.addItem(bookId, quantity, book);
            return cart;
        } catch (Carts.OutOfStockException e) {
            throw new OutOfStockException(e.getMessage());
        }
    }
    
    @GET
    public Carts getCart(@PathParam("customerId") int customerId) {
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
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
    public Carts updateItemQuantity(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId,
            Map<String, Integer> payload) {
        
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        
        if (bookId <= 0) {
            throw new BadRequestException("Invalid book ID: " + bookId);
        }
        
        // Extract and validate quantity parameter
        Integer quantity = payload.get("quantity");
        if (quantity == null) {
            throw new BadRequestException("Quantity is required");
        }
        
        Carts cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found.");
        }
        
        Books book = findBookById(bookId);
        
        try {
            cart.updateItem(bookId, quantity, book);
            return cart;
        } catch (Carts.OutOfStockException e) {
            throw new OutOfStockException(e.getMessage());
        }
    }
    
    @DELETE
    @Path("/items/{bookId}")
    public Carts removeItem(
            @PathParam("customerId") int customerId,
            @PathParam("bookId") int bookId) {
        
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }
        
        if (bookId <= 0) {
            throw new BadRequestException("Invalid book ID: " + bookId);
        }
        
        Carts cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found.");
        }
        
        Books book = findBookById(bookId);
        
        cart.removeItem(bookId, book);
        
        return cart;
    }
    
    @DELETE
    public void clearCart(@PathParam("customerId") int customerId) {
        if (customerId <= 0) {
            throw new CustomerNotFoundException("Invalid customer ID: " + customerId);
        }

        Carts cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found.");
        }
        
        cart.clear();
    }
}