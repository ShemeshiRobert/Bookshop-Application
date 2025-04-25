/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.resources;

import com.mycompany.bookshop.Authors;
import com.mycompany.bookshop.Books;
import com.mycompany.bookshop.exceptions.AuthorNotFoundException;
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
@Path("authors")
public class AuthorResource {
    private static List<Authors> authors = new ArrayList<>();
    private static int nextId = 0;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAuthor(Authors author){
        author.setId(nextId++);
        authors.add(author);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Authors> getAuthors(){
        return authors;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Authors getAuthorById(@PathParam("id") int id){
        for(Authors author: authors){
            if(author.getId() == id){
                return author;
            }
        }
        throw new AuthorNotFoundException("Author not Found");
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAuthor(@PathParam("id") int id, Authors updatedAuthor){
        for(int i = 0; i < authors.size(); i++){
            Authors author = authors.get(i);
            if(author.getId() == id){
                updatedAuthor.setId(id);
               authors.set(i, updatedAuthor);
                return;
            }       
        }
        throw new AuthorNotFoundException("Author not Found");
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteAuthor(@PathParam("id") int id) {
        for(int i = 0; i < authors.size(); i++){
            Authors author = authors.get(i);
            if(author.getId() == id){
                authors.remove(i);
                return;
            }       
        }
        throw new AuthorNotFoundException("Author not Found");
    }
    
    @GET
    @Path("/{id}/books")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Books> getBooksByAuthor(@PathParam("id") int id) {
        boolean authorExists = false;
        for (Authors author : authors) {
            if (author.getId() == id) {
                authorExists = true;
                break;
            }
        }   
        if (!authorExists) {
            throw new AuthorNotFoundException("Author not Found");
        }
        List<Books> bookList = new ArrayList<>();
        for (Books book : BookResource.getBooks()) {
            if (book.getAuthorId() == id) {
                bookList.add(book);
            }
        }
        return bookList;
    }
}
