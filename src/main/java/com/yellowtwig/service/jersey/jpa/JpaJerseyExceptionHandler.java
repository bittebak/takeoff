/*
 * Copyright (c) Marviq 2014
 */

package com.yellowtwig.service.jersey.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class is needed by Jersey, so that our custom {@link ContainerResponseFilter} gets 
 * executed in case of an unhandled exception is thrown by the resource provider. If this class is not used 
 * then JPA actions in {@link JpaJerseyResponseFilter} are not executed!
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@Provider
public class JpaJerseyExceptionHandler implements ExceptionMapper<Throwable> {
    private static final Logger logger = Logger.getLogger(JpaJerseyExceptionHandler.class.getName());

    @Override
    public Response toResponse(Throwable exception) {
        logger.log(Level.SEVERE, "Catching thrown exception and setting HTTP response code 500", exception);

        return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( exception.getMessage() ).build();
    }
}
