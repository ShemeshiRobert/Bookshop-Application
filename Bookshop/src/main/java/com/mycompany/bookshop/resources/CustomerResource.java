/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.resources;

import com.mycompany.bookshop.Customers;
import com.mycompany.bookshop.exceptions.CustomerNotFoundException;
import com.mycompany.bookshop.exceptions.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
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
@Path("customers")
public class CustomerResource {
    private static List<Customers> customers = new ArrayList<>();
    private static int nextId = 0;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer(Customers customer){
        if(customer.getName() == null){
            throw new InvalidInputException("Name is required.");
        }
        if (customer.getEmail()== null){
            throw new InvalidInputException("Email is required.");
        }
        if (customer.getPassword()== null){
            throw new InvalidInputException("Password is required.");
        }
        customer.setId(nextId++);
        customers.add(customer);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Customers> getCustomers(){
        return customers;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Customers getCustomerById(@PathParam("id") int id){
        for(Customers customer: customers){
            if(customer.getId() == id){
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer not Found");
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCustomer(@PathParam("id") int id, Customers updatedCustomer){
        for(int i = 0; i < customers.size(); i++){
            Customers customer = customers.get(i);
            if(customer.getId() == id){
                updatedCustomer.setId(id);
                customers.set(i, updatedCustomer);
                return;
            }       
        }
        throw new CustomerNotFoundException("Customer not Found");
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        for(int i = 0; i < customers.size(); i++){
            Customers customer = customers.get(i);
            if(customer.getId() == id){
                customers.remove(i);
                return;
            }       
        }
        throw new CustomerNotFoundException("Customer not Found");
    }
}
