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
public class Authors {
    private int id;
    private String name;
    private String biography;

    public Authors(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }
    
    public Authors() {

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
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Author name is required.");
        }
        this.name = name;
    }

    public String getBiography() {
        return biography; 
    }
    
    public void setBiography(String biography) {
    if (biography == null || biography.trim().isEmpty()) {
        throw new InvalidInputException("Biography is required.");
    }
    this.biography = biography;
}
}

