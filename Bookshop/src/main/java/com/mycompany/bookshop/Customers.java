/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

import com.mycompany.bookshop.exceptions.InvalidInputException;

/**
 *
 * @author Shemeshi Robert
 */
public class Customers {
    private String name;
    private String email;
    private String password;
    private int id;

    public Customers( String name, String email, String password) {              
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public Customers() {

    }

    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name; 
    }
    public void setName(String name) {
        if(name == null){
            throw new InvalidInputException("Title is required.");
        }
        this.name = name;
    }

    public String getEmail() {
        return email; 
    }
    public void setEmail(String email) {
        if (email == null){
            throw new InvalidInputException("Title is required.");
        }
        this.email = email; 
    }

    public String getPassword() { 
        return password;
    }
    public void setPassword(String password) {
        if (password == null){
            throw new InvalidInputException("Title is required.");
        }
        this.password = password;
    }
}
