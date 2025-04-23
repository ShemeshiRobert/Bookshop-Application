/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;
import com.mycompany.bookshop.Authors;
/**
 *
 * @author Shemeshi Robert
 */
public class Books {
    private int id;
    private String title;
    private Authors author;
    private String isbn;
    private int publicationYear;
    private double price;
    private int stockQuantity;
    
    public Books(){
    
    }
    
     public Books(int id, String title, Authors author, String isbn, int publicationYear, double price, int stockQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
     
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
     
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title; 
    }

    public Authors getAuthor() {
        return author; 
    }
    public void setAuthor(Authors author) {
        this.author = author; 
    }

    public String getIsbn() {
        return isbn; 
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn; 
    }

    public int getPublicationYear() {
        return publicationYear; 
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price; 
    }
    public void setPrice(double price) {
        this.price = price; 
    }

    public int getStockQuantity() {
        return stockQuantity; 
    }
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity; 
    }

}

