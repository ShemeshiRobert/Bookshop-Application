/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.resources;

import com.mycompany.bookshop.Books;
import com.mycompany.bookshop.Carts;
import com.mycompany.bookshop.Customers;
import com.mycompany.bookshop.Orders;
import com.mycompany.bookshop.exceptions.CartNotFoundException;
import com.mycompany.bookshop.exceptions.CustomerNotFoundException;
import com.mycompany.bookshop.exceptions.InvalidInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Shemeshi Robert
 */
@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private static final Map<Integer, List<Orders>> customerOrders = new HashMap<>();
    private static int nextOrderId = 1;
    
    private boolean customerExists(int customerId) {
        Customers customer = CustomerResource.getCustomerById(customerId);
        if (customer != null && customerId >= 0)
            return true;
        else
            return false;
    }
    
    @POST
    public Orders createOrder(@PathParam("customerId") int customerId) {
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        CartResource cartResource = new CartResource();
        Carts cart = cartResource.getCart(customerId);
        
        if (cart == null || cart.isEmpty()) {
            throw new CartNotFoundException("Cart is empty. Cannot create order.");
        }
        Orders newOrder = Orders.fromCart(nextOrderId++, customerId, cart);
        if (!customerOrders.containsKey(customerId)) {
            customerOrders.put(customerId, new ArrayList<>());
        }
        customerOrders.get(customerId).add(newOrder);
        
        cart.clear(CustomerResource.getCustomerById(customerId));
        return newOrder;
    }
    
    @GET
    public List<Orders> getCustomerOrders(@PathParam("customerId") int customerId) {
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    }
    
    @GET
    @Path("/{orderId}")
    public Orders getOrderById(
            @PathParam("customerId") int customerId,
            @PathParam("orderId") int orderId) {
        if (!customerExists(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        List<Orders> orders = customerOrders.getOrDefault(customerId, new ArrayList<>());
        for (Orders order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        throw new InvalidInputException("Order with ID " + orderId + " not found for customer " + customerId);
    }
}
