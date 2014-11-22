/*
 * Copyright (c) Marviq 2014
 */

package com.yellowtwig.service.jersey;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
public abstract class RestResource {
    protected static final Logger logger = Logger.getLogger(RestResource.class.getName());

    @Context
    protected ServletContext context;

    protected Response logAndReturn(Level logLevel, Response.Status responseStatus, String message, Object... arguments) {
        logger.log(logLevel, message, arguments);
        return Response.status(responseStatus).entity(MessageFormat.format(message, arguments)).build();
    }

    protected Response logAndReturn(Level logLevel, Response.Status responseStatus, String message, Throwable exception) {
        logger.log(logLevel, message, exception);
        return Response.status(responseStatus).entity(message).build();
    }

    protected Response logAndReturn(Level logLevel, Response.Status responseStatus, String message) {
        logger.log(logLevel, message);
        return Response.status(responseStatus).entity(message).build();
    }
}
