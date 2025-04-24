/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.exceptions.mappers;

/**
 *
 * @author Shemeshi Robert
 */
import com.mycompany.bookshop.exceptions.BookNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;

@Provider
public class BookNotFoundExceptionMapper implements ExceptionMapper<BookNotFoundException> {
    private static final Logger logger = LoggerFactory.getLogger(BookNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(BookNotFoundException exception) {
        logger.error("Book not found: {}", exception.getMessage(), exception);
        
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}

