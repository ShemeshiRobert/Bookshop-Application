/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;

/**
 *
 * @author Shemeshi Robert
 */
public class Authors {
    private int id;
    private String name;
    private String biography;

    public Authors(int id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }
    
    public Authors(){
        
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

