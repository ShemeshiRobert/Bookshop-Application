/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop;
import com.mycompany.bookshop.Authors;
import com.mycompany.bookshop.exceptions.InvalidInputException;
import com.mycompany.bookshop.resources.AuthorResource;
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
    AuthorResource authorResource;
    
     public Books(String title, int authorId, String isbn, int publicationYear, double price, int stockQuantity){
        authorResource = new AuthorResource();
        if (title == null) {
            throw new InvalidInputException("Title is required.");
        }
        if (authorId < 0 || authorResource.getAuthorById(authorId) == null) {
            throw new InvalidInputException("Valid authorId is required.");
        }
        if (isbn == null) {
            throw new InvalidInputException("ISBN is required.");
        }
        if (publicationYear <= 0 ) {
            throw new InvalidInputException("Invalid publication year.");
        }
        if (price < 0) {
            throw new InvalidInputException("Price cannot be negative.");
        }
        if (stockQuantity < 0) {
            throw new InvalidInputException("Enter valid Stock Quantity.");
        }                                        
        this.title = title;
        this.authorId = authorId;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockQuantity = stockQuantity;               
    }
     
    public Books() {

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

