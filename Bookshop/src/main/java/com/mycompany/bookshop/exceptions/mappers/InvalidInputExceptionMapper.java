/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookshop.exceptions.mappers;

/**
 *
 * @author Shemeshi Robert
 */
import com.mycompany.bookshop.exceptions.InvalidInputException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;

@Provider
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException> {
    private static final Logger logger = LoggerFactory.getLogger(InvalidInputExceptionMapper.class);

    @Override
    public Response toResponse(InvalidInputException exception) {
        logger.error("Invalid input: {}", exception.getMessage(), exception);
        
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
