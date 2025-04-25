/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;
import com.mycompany.bookshop.Authors;
import com.mycompany.bookshop.exceptions.InvalidInputException;
/**
 *
 * @author Shemeshi Robert
 */
public class Books {
    private int id;
    private String title;
    private int authorId;
    private String isbn;
    private int publicationYear;
    private double price;
    private int stockQuantity;
    
    public Books(){
    
    }
    
     public Books(String title, int authorId, String isbn, int publicationYear, double price, int stockQuantity) {
        this.title = title;
        this.authorId = authorId;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        if (stockQuantity >= 0)
            this.stockQuantity = stockQuantity;
        else
            throw new InvalidInputException("Enter valid Stock Quantity.");
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

    public int getAuthorId() {
        return authorId; 
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId; 
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
        if (stockQuantity < 0) {
            throw new InvalidInputException("Enter valid Stock Quantity.");
        }
        this.stockQuantity = stockQuantity;
    }

}

