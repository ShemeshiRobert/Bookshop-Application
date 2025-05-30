/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.resources;

import com.mycompany.bookshop.Authors;
import com.mycompany.bookshop.Books;
import com.mycompany.bookshop.exceptions.BookNotFoundException;
import com.mycompany.bookshop.exceptions.InvalidInputException;
import java.awt.PageAttributes;
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
@Path("books")
public class BookResource {
    private static List<Books> books = new ArrayList<>();
    private static int nextId = 0;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createBook(Books book){
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
        throw new InvalidInputException("Title is required");
        }
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new InvalidInputException("ISBN is required");
        }
        if (book.getAuthorId() == null) {
            throw new InvalidInputException("Author ID is required");
        }
        book.setId(nextId++);        
        books.add(book);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Books> getBooks(){
        return books;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Books getBookById(@PathParam("id") int id){
        for(Books book: books){
            if(book.getId() == id){
                return book;
            }
        }
        throw new BookNotFoundException("Book with ID " + id + " not found.");
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateBook(@PathParam("id") int id, Books updatedBook){
        for(int i = 0; i < books.size(); i++){
            Books book = books.get(i);
            if(book.getId() == id){
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return;
            }       
        }
        throw new BookNotFoundException("Book with ID " + id + " not found.");
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteBook(@PathParam("id") int id) {
        for(int i = 0; i < books.size(); i++){
            Books book = books.get(i);
            if(book.getId() == id){
                books.remove(i);
                return;
            }       
        }
    throw new BookNotFoundException("Book with ID " + id + " not found.");
    }
}
