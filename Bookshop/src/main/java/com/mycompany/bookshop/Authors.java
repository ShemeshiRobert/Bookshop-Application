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
        if(name == null){
            throw new InvalidInputException("Title is required.");
        }
        if(biography == null){
            throw new InvalidInputException("Title is required.");
        }
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
        this.name = name; 
    }

    public String getBiography() {
        return biography; 
    }
    
    public void setBiography(String biography) {
        this.biography = biography; 
    }
}

